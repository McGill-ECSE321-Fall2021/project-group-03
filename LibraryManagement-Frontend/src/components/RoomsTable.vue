<template>
  <div>
    <table>
      <tr>
        <th>Name</th>
        <th>Type</th>
        <th>Capacity</th>
      </tr>

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
</script>

<style scoped>

  table {
    border-collapse: collapse;
    width: 100%;
    }

.click-appear {
  cursor: pointer;
}

 td,th {
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

</style>