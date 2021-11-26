<template>
  <div>
    <table>
      <tr>
        <th></th>
        <th>Name</th>
        <th>Type</th>
        <th>Genre</th>
        <th>Is Available</th>
        <th>Description</th>
      </tr>
      <tr>
        <td><a href="/#/browse/Moby%20Dick">View</a></td>
        <td>Moby Dick</td>
        <td>Book</td>
        <td>Adventure</td>
        <td>Yes</td>
        <td>guy with whale</td>
      </tr>
      <tr>
        <td><a href="/#/browse/Moby%20Dick">View</a></td>
        <td>Moby Dick</td>
        <td>Book</td>
        <td>Adventure</td>
        <td>Yes</td>
        <td>guy with whale</td>
      </tr>
      <tr>
        <td><a href="/#/browse/Moby%20Dick">View</a></td>
        <td>Moby Dick</td>
        <td>Book</td>
        <td>Adventure</td>
        <td>Yes</td>
        <td>guy with whale</td>
      </tr>

      <tr v-for="title in titles" :key="title">
        <td><router-link :to="routerlink(title.name)">View</router-link></td>
        <td>{{ title.name }}</td>
        <td>{{ title.titleType }}</td>
        <td>{{ title.genre }}</td>
        <td>{{ displayAvailability(title.available) }}</td>
        <td>{{ title.description }}</td>
      </tr>
    </table>
  </div>
</template>
<script src="./titles.js">
</script>
<style>
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
</style>