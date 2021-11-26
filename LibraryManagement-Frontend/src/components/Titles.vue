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
                <td><a href="">View</a></td>
                <td>{{title.name}}</td>
                <td>{{title.type}}</td>
                <td>{{title.genre}}</td>
                <td>{{title.isAvailable}}</td>
                <td>{{title.description}}</td>
            </tr>
            <tr>
          
          <td>
              <input type="text" v-model="titleGenre" placeholder="Title genre"> 
          </td>
          <td>
              <input type="text" v-model="titleDescription" placeholder="Title description"> 
          </td>
          <td>
              <input type="text" v-model="titleIsAvailable" placeholder="Title status"> 
          </td>
          <td>
              <input type="text" v-model="titleType" placeholder="Title type"> 
          </td>
          <td>
              <input type="text" v-model="titleName" placeholder="Title Name"> 
          </td>
          <td>
              <button v-bind:disabled="!titleName" @click="createTitle(titleName)">Create</button>
          </td>
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