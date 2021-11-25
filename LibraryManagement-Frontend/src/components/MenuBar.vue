<template>
<div>
    <div class="header top">
        TASLA Library
        <a class="login" href="/#/login">Login</a>
    </div>
    <div class="menu-bar">
        <a class="home" href="/#/">Home</a>
        <a class="browse" href="/#/browse">Browse Titles</a>
        <a class="room-booking" href="/#/rooms">Book a Room</a>
        <a class="account" href="/#/account">Account Settings</a>
        <a class="tools" href="/#/tools">Library Tools</a>
    </div>
</div>
</template>
<script>
import Login from '../views/Login.vue'

export default {
    name: 'MenuBar',
    components: {
        Login
    },
    mounted: function currentPage(){
        const url = window.location.href
        const host = window.location.host
        const path = url.split(host)[1]
    
        if (path == "/#/"){
            document.querySelector(".home").className += " active"
        }

        if (path == "/#/browse"){
            document.querySelector(".browse").className += " active"
        }

        if (path == "/#/account"){
            document.querySelector(".account").className += " active"
        }

        if (path == "/#/rooms"){
            document.querySelector(".room-booking").className += " active"
        }

        if (path == "/#/tools"){
            document.querySelector(".tools").className += " active"
        }

    }

}

</script>
<style scoped>

    .top{
        vertical-align: middle;
        text-align: center;
        border-bottom: solid;
    }

  .header {
    background-color: aliceblue; 
    color: darkslategray;
    font-size: 35px;
    font-weight: 700;
    padding: 10px;
  }

  .login{
      text-decoration: dashed;
      background-color: darkslategrey;
      color: aliceblue;
      padding: 5px;
      width: 100px;
      border-radius: 10px;
      font-size: 15px;
      float: right;
      margin-top: 1.75%;
  }

  .login:hover{
      text-decoration: dashed;
      background-color: aliceblue;
      border-style: solid;
      color: darkslategray;
      padding: 5px;
      width: 100px;
      border-radius: 10px;
      margin-top: 1.75%;
  }

    .menu-bar {
        overflow: hidden;
        background-color: darkslategray;
        border-bottom: solid;
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