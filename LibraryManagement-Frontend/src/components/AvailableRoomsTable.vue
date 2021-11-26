<template>
  <div class="table">
    <table>
      <tr>
        <th>Time</th>
      </tr>
      <!-- ADD ID WHEN  -->
      <tr class="click-appear" id="" ref="r1">
        <td>7:00am-8:00am</td>
      </tr>
      <tr class="click-appear" id="2" @click="clickRow()">
        <td>8:00am-9:00am</td>
      </tr>
      <tr class="click-appear" id="3" @click="clickRow(this.id)">
        <td>9:00am-10:00am</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  methods: {
    clickRow() {
      console.log(this.$refs);
      //   element.classList.add("selected");
      //   document.querySelector(element).className += " selected";
    },
  },
};
</script>

<style scoped>
.table {
  border-collapse: collapse;
  padding-left: 80px;
  padding-right: 80px;
  padding-bottom: 10px;
}

.click-appear {
  cursor: pointer;
}

.selected {
  background-color: blue;
}
</style>