<template>
<div>
    <MenuBar />
<div class="Titles section">
        <div class="header">Filter</div>
        <div class="content">
                <input type="text" id="title-name" placeholder="Search title...">
                <button @click="filterSearch()" class="submit-btn">Search</button>
                <br>
                <button @click="reveal()" class="more-filters-btn">More Filters</button>
                <div class="more-filters" hidden=true>                 
                    <label for="title-type">Type</label>
                    <select name="title-type" class="filter-input" id="type">
                        <option value="all">All</option>
                        <option value="Book">Book</option>
                        <option value="Movie">Movie</option>
                        <option value="Music-album">Music Album</option>
                        <option value="Newspaper">Newspaper</option>
                        <option value="Archive">Archive</option>
                    </select>
                    <br>
                    <label for="genre">Genre</label>
                    <select name="genre" class="filter-input" id="genre">
                        <option value="all">All</option>
                        <option value="Adventure">Adventure</option>
                        <option value="Action">Action</option>
                        <option value="Comedy">Comedy</option>
                        <option value="Horror">Horror</option>
                    </select>
                    <br>
                        <label for="title-type">Availability</label>
                        <select id="availability">
                            <option value="all">All</option>
                            <option value="Yes">✓</option>
                            <option value="No">✗</option>
                        </select>
                </div>
        </div>
        <div class="header">Titles</div>
        <div class="content titles">
            <table>
      <tr class="first-row">
        <td>Name</td>
        <td>Type</td>
        <td>Genre</td>
        <td>Available</td>
        <td>Description</td>
        <td>Title Id</td>
      </tr>
        <tr class="table-data">
            <td class="title-name">Wizard of Oz</td>
            <td class="title-type">Book</td>
            <td class="title-genre">Adventure</td>
            <td class="title-isAvailable">✓</td>
            <td>description</td>
            <td>titleId</td>
        </tr>
        <tr class="table-data">
            <td class="title-name">Rush Hour</td>
            <td class="title-type">Movie</td>
            <td class="title-genre">Comedy</td>
            <td class="title-isAvailable">✗</td>
            <td>description</td>
            <td>titleId</td>
        </tr>
        <tr class="table-data">
            <td class="title-name">Gazette</td>
            <td class="title-type">Newspaper</td>
            <td class="title-genre">Action</td>
            <td class="title-isAvailable">✓</td>
            <td>description</td>
            <td>titleId</td>
        </tr>  
      <tr
        v-for="title in titles"
        :key="title"
        @click="clickRow(title.name)"
        class="click-appear">

        <td>{{ title.name }}</td>
        <td>{{ title.titleType }}</td>
        <td>{{ title.genre }}</td>
        <td>{{ displayAvailability(title.available) }}</td>
        <td>{{ title.description }}</td>
        <td>{{ title.titleId }}</td>
      </tr>
    </table>
        </div>
    </div>
</div>
</template>

<script src="./titles.js">
</script>

<style scoped>

    table {
    border-collapse: collapse;
    width: 100%;
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

    .header {
        color: darkslategray;
        font-size: 35px;
        font-weight: 700;
        padding: 10px;
    }

    .header2 {
        color: darkslategray;
        font-weight: 700;
        padding: 10px;
        font-size: 20px;
        padding-bottom: 10px;
    }

    .info {
        font-weight: 400;
        color: teal;
    }

    .content {
        color: teal;
        padding-left: 10px;
        line-height: 1.6;
    }

    body {
        background-color: aliceblue;
    }

    .submit-btn {
        background-color: darkslategrey;
        color: aliceblue;
        padding: 5px;
        width: 100px;
        border-radius: 10px;
        margin-left: 10px;
    }

    .submit-btn:hover {
        background-color: aliceblue;
        color: darkslategray;
        width: 100px;
        padding: 5px;
        border-radius: 10px;
        margin-left: 10px;
    }

    .more-filters-btn {
        color: darkslategray;
        margin-bottom: 10px;
        margin-top: 10px;
        border-radius: 10px;
    }

    .title-name {
        border-radius: 6px;
        width: 500px
    }

    .filter-input {
        border-radius: 6px;
        width: 200px;
        margin-left: 10px;
    }

</style>
