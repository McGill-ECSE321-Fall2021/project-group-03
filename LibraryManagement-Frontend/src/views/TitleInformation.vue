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
                <td><a href="">Reserve</a></td>
                <td>Moby Dick</td>
                <td>Book</td>
                <td>Adventure</td>
                <td>Yes</td>
                <td>guy with whale</td>
            </tr>
            <tr>
                <td><a href="">Reserve</a></td>
                <td>Moby Dick</td>
                <td>Book</td>
                <td>Adventure</td>
                <td>Yes</td>
                <td>guy with whale</td>
            </tr>
            <tr>
                <td><a href="">Reserve</a></td>
                <td>Moby Dick</td>
                <td>Book</td>
                <td>Adventure</td>
                <td>Yes</td>
                <td>guy with whale</td>
            </tr>

            <tr v-for="title in titles" :key="title">
                <td><a href="">Reserve</a></td>
                <td>{{title.name}}</td>
                <td>{{title.type}}</td>
                <td>{{title.genre}}</td>
                <td>{{title.isAvailable}}</td>
                <td>{{title.description}}</td>
            </tr>
        </table>
    </div>
</template>
<script src="./titles.js">
</script>
<style>