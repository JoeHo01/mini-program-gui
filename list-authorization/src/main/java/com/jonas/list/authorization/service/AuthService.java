package com.jonas.list.authorization.service;

import com.jonas.list.authorization.component.exception.ExceptionInfo;
import com.jonas.list.authorization.component.exception.WebException;
import com.jonas.list.authorization.dao.AuthDAO;
import com.jonas.list.authorization.dao.RangeDAO;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/28
 */
@Slf4j
@Service
public class AuthService {
	private final byte[] HEX_DECIMAL = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

	private final String CLAIM_KEY_ID = "id";

	private final String CLAIM_KEY_ACCOUNT = "account";

	private JwtBuilder jwtBuilder;

	private JwtParser jwtParser;

	@Value("${auth.secret}")
	private String secret;

	@Value("${auth.valid}")
	private long validTime;

	private AuthDAO authDAO;

	private RangeDAO rangeDAO;

	public AuthService(AuthDAO authDAO, RangeDAO rangeDAO) {
		this.authDAO = authDAO;
		this.rangeDAO = rangeDAO;
	}

	public boolean authorization(long rangeId, String compact) throws WebException {
		try {
			Long id = jwtParser.parseClaimsJws(compact).getBody().get(CLAIM_KEY_ID, Long.class);
			if (!rangeDAO.validate(rangeId, id))
				throw new WebException(ExceptionInfo.AUTH_VALIDATE_ERROR_RANGE);
			return true;
		} catch (JwtException e) {
			log.error(ExceptionInfo.AUTH_VALIDATE_ERROR_TOKEN.getMessage() + e.getMessage(), e);
			throw new WebException(ExceptionInfo.AUTH_VALIDATE_ERROR_TOKEN);
		}
	}

	public String signIn(String account, String password) throws WebException {
		Long id = authDAO.validatePassword(account, encodePassword(password));
		if (id == null)
			throw new WebException(ExceptionInfo.AUTH_VALIDATE_ERROR_WRONG_PWD);

		if (!authDAO.validateActive(account)) {
			validateEmail(account);
			throw new WebException(ExceptionInfo.AUTH_VALIDATE_ERROR_INACTIVE);
		}
		return signature(id);
	}

	public long signUp(String account, String password) throws WebException {
		if (authDAO.validateActive(account)) {
			throw new WebException(ExceptionInfo.AUTH_INSERT_ERROR_DUPLICATE);
		}else {
			authDAO.deleteAuth(account);
		}
		validateEmail(account);
		return authDAO.addAuth(account, encodePassword(password));
	}

	public boolean validateEmail(String email) {
		// todo email server
		return true;
	}

	public String active(String compact) throws WebException {
		try {
			String account = jwtParser.parseClaimsJws(compact).getBody().get(CLAIM_KEY_ACCOUNT, String.class);
			long id = authDAO.activeAuth(account);
			if (id == 0)
				throw new WebException(ExceptionInfo.AUTH_VALIDATE_ERROR_TO_ACTIVE);
			else
				return signature(id);
		} catch (JwtException e) {
			log.error(ExceptionInfo.AUTH_VALIDATE_ERROR_TOKEN.getMessage() + e.getMessage(), e);
			throw new WebException(ExceptionInfo.AUTH_VALIDATE_ERROR_TOKEN);
		}
	}

	/** **************** private methods **************** */

	private String signature(long id) {
		return jwtBuilder
				.claim(CLAIM_KEY_ID, id)
				.setExpiration(new Date(System.currentTimeMillis() + validTime))
				.compact();
	}

	private String encodePassword(String password) {
		return new String(saltMD5(password, password));
	}

	@PostConstruct
	private void initSecretKey() {
		SignatureAlgorithm signatureHS256 = SignatureAlgorithm.HS256;
		SecretKey secretKey = new SecretKeySpec(Base64Utils.encode(saltMD5(secret, "#f7%4Pn1*!GkemU6")), signatureHS256.getJcaName());
		this.jwtBuilder = Jwts.builder().signWith(signatureHS256, secretKey);
		this.jwtParser = Jwts.parser().setSigningKey(secretKey);
	}

	private byte[] saltMD5(String source, String salt) {
		byte[] binaryData = salt(source, salt);
		byte[] buffer = new byte[32];
		for (int i = 0; i < 16; ++i) {
			int low = binaryData[i] & 15;
			int high = (binaryData[i] & 240) >> 4;
			buffer[i * 2] = HEX_DECIMAL[high];
			buffer[i * 2 + 1] = HEX_DECIMAL[low];
		}
		return buffer;
	}

	private byte[] salt(String source, String salt) {
		byte[] result = new byte[16];
		byte[] sourceBytes = source.getBytes();
		byte[] saltBytes = salt.getBytes();
		for (int i = 0; i < 16; i++) {
			for (int j = i; j < sourceBytes.length; j += saltBytes.length) {
				result[i] += sourceBytes[j];
			}
			result[i] += saltBytes[i % saltBytes.length];
		}
		return result;
	}
}
