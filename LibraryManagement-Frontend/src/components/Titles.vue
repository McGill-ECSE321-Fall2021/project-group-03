<template>
  <div>
    <table>
      <tr class="first-row">
        <td>Name</td>
        <td>Type</td>
        <td>Genre</td>
        <td>Available</td>
        <td>Description</td>
        <td>Title Id</td>
      </tr>
      <tr
        v-for="title in titles"
        :key="title"
        @click="clickRow(title.name)"
        class="click-appear"
      >
        <td>{{ title.name }}</td>
        <td>{{ title.titleType }}</td>
        <td>{{ title.genre }}</td>
        <td>{{ displayAvailability(title.available) }}</td>
        <td>{{ title.description }}</td>
        <td>{{ title.titleId }}</td>
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

.click-appear {
  cursor: pointer;
}

.first-row td {
  font-size: 20px;
  font-weight: 40px;
  color: black;
}
</style>