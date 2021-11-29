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


</style>