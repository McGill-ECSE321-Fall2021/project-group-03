<template>
  <div>
    <MenuBar />
    <div class="header-2">Available Rooms</div>
    <!-- <div class="type-selector" @change="selectFilter()">
      <label for="room-type">Room Type: </label>
      <select name="room-type" id="type-selector">
        <option value="all">All</option>
        <option value="Study">Study</option>
        <option value="Event">Event</option>
      </select>
    </div> -->
    <div class="header-3">Click on room name to reserve</div>

    <div class="content titles">
      <table>
        <tr>
          <td>Type</td>
          <td>Capacity</td>
        </tr>
        <tr
          class="click-appear"
          v-for="room in displayRooms"
          :key="room"
          @click="clickRow(room.roomId)"
        >
          <td>{{ room.roomType }}</td>
          <td>{{ room.capacity }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script src="./rooms.js"></script>

<style scoped>
.header-2 {
  padding-top: 10px;
  color: darkslategray;
  font-size: 30px;
  font-weight: 500;
  text-align: center;
}

.header-3 {
  color: darkslategray;
  font-size: 18px;
  font-weight: 350;
  text-align: center;
}

.type-selector {
  padding-left: 20px;
}

table {
  border-collapse: collapse;
  width: 60%;
  margin: 20px;
  margin-left: auto;
  margin-right: auto;
}

.click-appear {
  cursor: pointer;
}

td,
th {
  border: 1px solid aliceblue;
  text-align: left;
  padding: 8px;
  text-align: center;
}

tr:nth-child(even) {
  background-color: aliceblue;
}

table :first-child {
  font-size: 20px;
  font-weight: 550;
  color: black;
}
</style>