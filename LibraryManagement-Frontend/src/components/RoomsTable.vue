<template>
  <div class="table">
    <table>
      <tr>
        <th>Name</th>
        <th>Type</th>
        <th>Capacity</th>
      </tr>
      <!-- @click="clickRow() -->
      <tr class="click-appear">
        <td>No Macs Allowed Room</td>
        <td>Party</td>
        <td>40</td>
      </tr>

      <tr class="click-appear">
        <td>Kildare Room</td>
        <td>Study</td>
        <td>3</td>
      </tr>

      <tr class="click-appear">
        <td>Liamo's Private Room</td>
        <td>Study</td>
        <td>4</td>
      </tr>

      <tr class="click-appear" v-for="room in rooms" :key="room">
        <td>{{ room.roomId }}</td>
        <td>{{ room.roomType }}</td>
        <td>{{ room.capacity }}</td>
      </tr>
    </table>
  </div>
</template>

<script src="./rooms.js">
// <script>
// export default {
//   methods: {
//     clickRow() {
//       console.log("hi");
//       // UPDATE LINK
//       window.location.href = "/#/browse/room/Liamos%20Private%20Room";
//     },
//   },
// };
//
</script>

<style scoped>
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