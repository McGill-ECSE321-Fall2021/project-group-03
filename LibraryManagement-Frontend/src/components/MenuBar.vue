<template>
<div>
    <div class="header">Stdio Library</div>
    <div class="menu-bar">
        <a class="home" href="">Home</a>
        <a class="browse" href="">Browse Titles</a>
        <a class="room-booking" href="">Book a Room</a>
        <a class="account" href="">Account Settings</a>
    </div>
</div>
</template>
<script>
    // var url = window.location.href
    // var host = window.location.host

    // console.log(url.split(host)[1])
    // path = url.split(host)[1]

    // if (path == "/#/"){
    //     document.querySelector(".home").className += "active"
    // }
</script>
<style scoped>
  .header {
    background-color: aliceblue; 
    color: darkslategray;
    font-size: 35px;
    font-weight: 700;
    padding: 10px;
  }

    .menu-bar {
        overflow: hidden;
        background-color: darkslategray;
    }

    .menu-bar a{
        color: aliceblue;
        float: left;
        text-align: center;
        font-size: 17px;
        padding: 20px;
        text-decoration: dashed;
    }

    .menu-bar a:hover {
        background-color: aliceblue;
        color: darkslategray;
    }

    .menu-bar a.active {
        background-color: aliceblue;
        color: darkslategray;
    }
</style>