<template>
<div>
    <MenuBar />
    <div class="header" id="title-name">{{$route.params.name}}</div>
    <div class="content">
        <div class="header2">Type <span class="info">Book</span></div>
        <div class="header2">Genre <span class="info">Adventure</span></div>
        <div class="header2">Is Available <span class="info">Yes</span></div>
        <div class="header2">Description </div>
        <p class="info">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
        <button @click="reserveTitle()" id="reserve-title-btn" class="submit-btn">Reserve Title</button>
    </div>
</div>
</template>
<script src="./titleInformation.js">
</script>
<style>
.header {
    color: darkslategray;
    font-size: 35px;
    font-weight: 700;
    padding: 20px;
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
    padding: 10px;
    font-size: 15px;
}

.content {
    color: teal;
    padding-left: 10px;
    line-height: 1.6;
}


.submit-btn {
    background-color: darkslategrey;
    color: aliceblue;
    padding: 10px;
    width: 200px;
    border-radius: 10px;
    margin-left: 10px;
}

.submit-btn:hover {
    background-color: aliceblue;
    color: darkslategray;
    width: 200px;
    padding: 10px;
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