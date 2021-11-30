<template>
  <div class="table">
    <input type="date" id="birthday" name="birthday" />
    <table>
      <tr>
        <th>Time</th>
        <th>Date</th>
      </tr>
      <tr
        class="click-appear"
        v-for="roomReservation in roomReservations"
        :key="roomReservation"
        @click="clickRow(roomReservation)"
      >
        <th>{{ roomReservation.startTime }} - {{ roomReservation.endTime }}</th>
        <th>{{ roomReservation.date }}</th>
      </tr>
    </table>
  </div>
</template>

<script src="./timeslots.js"></script>

<style scoped>
table {
  border-collapse: collapse;
  width: 40%;
  margin-top: 0px;
  margin-left: auto;
  margin-right: auto;
}

td,
th {
  border: 1px solid aliceblue;
  text-align: center;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: aliceblue;
}

.click-appear {
  cursor: pointer;
}

#birthday {
  margin: auto;
  display: block;
  margin-bottom: 20px;
  margin-top: 20px;
}
</style>