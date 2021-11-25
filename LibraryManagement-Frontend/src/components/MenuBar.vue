<template>
<div @onload="currentPage()">
    <div class="header">Stdio Library</div>
    <div class="menu-bar">
        <a class="home" href="/#/">Home</a>
        <a class="browse" href="/#/browse">Browse Titles</a>
        <a class="room-booking" href="">Book a Room</a>
        <a class="account" href="/#/account">Account Settings</a>
    </div>
</div>
</template>
<script>
export default {
    name: 'MenuBar',
    methods: {
        currentPage(){
            console.log(123)
            var url = window.location.href
            var host = window.location.host
            
            path = url.split(host)[1]
        
            if (path == "/#/"){
                document.querySelector(".home").className += "active"
            }
        }
    }

}
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