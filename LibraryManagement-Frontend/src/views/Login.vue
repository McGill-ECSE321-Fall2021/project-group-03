<template>
  <div>
    <!-- <MenuBar /> -->
    <a class="home-btn" href="/#/">Home</a>
    <br />
    <div class="login-form">
      <form class="form" action="">
        <label class="header2" for="username">Username</label>
        <input type="text" id="login-username" name="username" />
        <br />
        <label class="header2" for="password">Password</label>
        <input type="password" id="login-password" />
        <br />
        <label for="librarianCheck" class="header2">Librarian Account</label>
        <input type="checkbox" class="isLibrarian" />
        <br />
        <input
          @click="loginUser()"
          class="login-btn"
          type="button"
          value="Login"
        />
      </form>
    </div>

    <br />

    <div class="register">
      <button @click="fadeIn()" class="no-account-btn">
        Don't Have an Account?
      </button>
    </div>
    <div class="register-form" hidden="true">
      <form class="form" action="">
        <label class="header2" for="username">Username</label>
        <input type="text" name="username" />
        <br />
        <label class="header2" for="password">Password</label>
        <input type="password" />
        <br />
        <label class="header2" for="email">Email</label>
        <input type="password" placeholder="youremail@library.com" />
        <br />
        <label class="header2" for="address">Address</label>
        <input type="password" placeholder="514 BeAgile road" />
        <br />
        <input class="login-btn" type="submit" value="Sign Up" />
      </form>
    </div>
  </div>
</template>
<script src="./login.js">
</script>
<style>
.home-btn {
  text-decoration: dashed;
  background-color: darkslategrey;
  color: aliceblue;
  padding: 5px;
  width: 100px;
  border-radius: 10px;
  font-size: 15px;
  margin: 10px;
}

.home-btn:hover {
  text-decoration: dashed;
  background-color: aliceblue;
  color: darkslategray;
  padding: 5px;
  width: 100px;
  border-radius: 10px;
  font-size: 15px;
  border-style: solid;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
}

.fade-in {
  animation-name: fadeIn;
  animation-duration: 2s;
}

.header2 {
  color: darkslategray;
  font-weight: 700;
  padding-bottom: 10px;
  margin: 10px;
}

.register {
  text-align: center;
}

.login-btn {
  text-decoration: dashed;
  background-color: darkslategrey;
  color: aliceblue;
  padding: 5px;
  width: 300px;
  border-radius: 10px;
  font-size: 15px;
}

.login-btn:hover {
  text-decoration: dashed;
  background-color: aliceblue;
  border-style: solid;
  color: darkslategray;
  padding: 5px;
  width: 300px;
  border-radius: 10px;
}

.form {
  text-align: center;
  margin-top: 20px;
}

.no-account-btn {
  text-decoration: dashed;
  background-color: darkslategrey;
  color: aliceblue;
  padding: 5px;
  width: 300px;
  border-radius: 10px;
  font-size: 15px;
  margin-top: 1.75%;
}

.no-account-btn:hover {
  text-decoration: dashed;
  background-color: aliceblue;
  color: darkslategray;
  padding: 5px;
  width: 300px;
  border-radius: 10px;
  font-size: 15px;
  margin-top: 1.75%;
}
</style>