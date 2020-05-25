<template>
    <div>
        <ticketLine v-for="(line, i) in lines" v-bind:key="i"
                    :vm_status="line.status" :vm_code="line.code" :vm_summary="line.summary" :vm_deadline="line.deadline"
        />
    </div>
</template>

<script>
  import ticketLine from "../elements/TicketLine";

  export default {
    name: "dashboard",
    components: {
      ticketLine
    },
    data() {
      return {
        lines: [
          {
            status: 0,
            code: "DS-12",
            summary: "Test Summary",
            deadline_date: new Date()
          },
          {
            status: 1,
            code: "DS-13",
            summary: "Test Summary",
            deadline_date: new Date()
          }
        ]
      }
    }
  }
</script>

<style scoped>

</style>