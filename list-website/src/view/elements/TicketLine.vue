<template>
    <div>
        <el-row class="row-bg">
            <el-col :span="1">
                <div class="grid-content bg-purple"></div>
            </el-col>
            <el-col :span="2">
                <div class="grid-content icon-warp bg-purple-dark"><i :class="class_status_icon"></i></div>
            </el-col>
            <el-col :span="3">
                <div class="grid-content bg-purple">{{vm_code}}</div>
            </el-col>
            <el-col :span="15">
                <div class="grid-content bg-purple-dark">{{vm_summary}}</div>
            </el-col>
            <el-col :span="2">
                <div class="grid-content bg-purple" v-if="vm_deadline">{{message_deadline}}</div>
            </el-col>
            <el-col :span="1">
                <div class="grid-content bg-purple-light"></div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
  import {formatDate} from "../../common/util/DateUtil";

  export default {
    name: "TicketLine",
    props: {
      vm_status: {
        type: Number
      },
      vm_code: {
        type: String
      },
      vm_summary: {
        type: String
      },
      vm_deadline: {
        type: Date
      }
    },
    computed: {
      class_status_icon: function () {
        switch (this.vm_status) {
          case 0:
            return "el-icon-document";
          case 1:
            return "el-icon-check";
          default:
            return "";
        }
      },
      message_deadline: function () {
        return formatDate(this.vm_deadline);
      }
    }
  }
</script>

<style scoped>
    .el-row {
        margin-bottom: 20px;
    }
    .el-col {
        border-radius: 4px;
    }

    .row-bg {
        padding: 2px 0;
        background-color: #f9fafc;
        /*border-bottom-width: 2px;*/
        /*border-bottom-style: solid;*/
        /*border-bottom-color: #99a9bf;*/
    }

    .grid-content {
        border-radius: 0px;
        min-height: 18px;
        text-align: left;
        font-size: 20px;
        height:fit-content;
    }

    /*.icon-warp {*/
    /*    font-size: 36px;*/
    /*}*/

    /*.bg-purple-dark {*/
    /*    background: #99a9bf;*/
    /*}*/
    /*.bg-purple {*/
    /*    background: #d3dce6;*/
    /*}*/
    /*.bg-purple-light {*/
    /*    background: #e5e9f2;*/
    /*}*/

</style>