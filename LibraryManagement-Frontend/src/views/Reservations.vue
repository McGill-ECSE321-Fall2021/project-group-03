<template>
  <div id="reservation-section" @load="getClientTitleReservations()">
    <MenuBar />
    <div>
      <div class="header">Current Title Reservations</div>
      <table>
        <tr class="first-row">
          <td>Name</td>
          <td>Return Date</td>
          <td>Checked Out</td>
        </tr>
        <tr
          v-for="titleReservation in this.titleReservations"
          :key="titleReservation"
          class="click-appear"
        >
          <td>{{ titleReservation.title.name }}</td>
          <td>{{ titleReservation.returnDate }}</td>
          <td>{{ titleReservation.checkedOut }}</td>
        </tr>
      </table>
    </div>
    <div class="header">Current Room Reservations</div>
    <!-- <table>
      <tr class="first-row">
        <td>Id</td>
        <td>Time</td>
      </tr>
      <tr
        v-for="roomReservation in this.roomReservations"
        :key="roomReservation"
        class="click-appear"
      >
        <td>{{ reservation.roomId }}</td>
        <td>{{ roomReservation.startTime }} - {{ roomReservation.endTime }}</td>
      </tr>
    </table> -->
  </div>
</template>

<script src="./reservations.js">
</script>

<style scoped>
table {
  border-collapse: collapse;
  width: 100%;
}

td,
th {
  border: 1px solid aliceblue;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: aliceblue;
}

.click-appear {
  cursor: pointer;
}

.first-row td {
  font-size: 20px;
  font-weight: 40px;
  color: black;
}

.header {
  color: darkslategray;
  font-size: 35px;
  font-weight: 700;
  padding: 10px;
}
</style>
