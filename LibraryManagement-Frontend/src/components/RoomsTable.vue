<template>
  <div class="table">
    <table>
      <tr>
        <th>Name</th>
        <th>Type</th>
        <th>Location</th>
        <th>Capacity</th>
      </tr>
      <!-- v-on:click="clickRow('party')" -->
      <tr class="click-appear" @click="clickRow()">
        <td>No Macs Allowed Room</td>
        <td>Party</td>
        <td>2-B9</td>
        <td>40</td>
      </tr>
      <tr class="click-appear" @click="clickRow()">
        <td>Kildare Room</td>
        <td>Study</td>
        <td>2-D3</td>
        <td>3</td>
      </tr>
      <tr class="click-appear" @click="clickRow()">
        <td>Liamo's Private Room</td>
        <td>Study</td>
        <td>4-A2</td>
        <td>4</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  methods: {
    clickRow() {
      console.log("hi");
      // UPDATE LINK
      window.location.href = "/#/browse/room/Liamos%20Private%20Room";
    },
  },
};
</script>

<style>
.table {
  border-collapse: collapse;
  width: 100%;
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 20px;
}

.click-appear {
  cursor: pointer;
}
</style>