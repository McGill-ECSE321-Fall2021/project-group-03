<template>
  <div>
    <div class="header top">
      TASLA Library
      <a class="login" href="/#/login">Login</a>
      <a class="login" id="logout-btn" href="/#/" @click="logoutUser()" hidden=true>Logout</a>
    </div>
    <div class="menu-bar">
      <a hidden=true class="home" href="/#/">Home</a>
      <a hidden=true class="browse" href="/#/browse">Browse Titles</a>
      <a hidden=true class="room-booking" href="/#/rooms">Book a Room</a>
      <a hidden=true class="account" href="/#/account">Account Settings</a>
      <a hidden=true class="tools" href="/#/tools">Library Tools</a>
      <a hidden=true class="management" href="/#/management">Management</a>
    </div>
  </div>
</template>
<script src="./menuBar.js">

</script>
<style scoped>
.top {
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

#logout-btn{
  margin-right: 10px;
}

.login {
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

.login:hover {
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

.menu-bar a {
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