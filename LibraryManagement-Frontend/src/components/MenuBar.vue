<template>
<div>
    <div class="header">Weclome to the Library Management System!</div>
    <div class="menu-bar">
        <a class="" href="">Home</a>
        <a href="">Browse Titles</a>
        <a href="">Library Information</a>
        <a href="">Account Settings</a>
    </div>
</div>
</template>
<script>
</script>
<style>

  .header {
    color: white;
    background-color: cornflowerblue;
    font: 17px;
    padding: 20px;
  }

    .menu-bar {
        background-color:dimgrey;
        overflow: hidden;
    }

    .menu-bar a{
        color: white;
        float: left;
        text-align: center;
        font-size: 17px;
        padding: 20px;
        text-decoration: dashed;
    }

    .menu-bar a:hover {
        background-color: cornflowerblue;
    }

    .menu-bar a.active {
        background-color: cornflowerblue;
        color: white;
    }
</style>