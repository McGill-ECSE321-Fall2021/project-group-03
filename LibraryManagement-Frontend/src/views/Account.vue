<template>
  <div>
    <MenuBar />
    <div class="header">Account Info</div>
    <div class="content">
      <div class="header2">
        Username:
        <span class="info username" id="user-username"> </span>
      </div>
      <div class="header2">
        Password:
        <span class="info password-hidden">*******</span>
        <span hidden="true" class="info password" id="user-password"></span>
        <input @click="showPass()" type="checkbox" class="show-pass" />
        <input
          @click="editPassword()"
          class="edit-btn"
          type="submit"
          value="Edit"
        />
        <input
          class="edit edit-password"
          id="update-password"
          placeholder="new password"
          hidden="true"
          type="text"
        />
      </div>
      <div class="header2" id="email-section">
        Email:
        <span class="info email" id="user-email"></span>
        <input
          @click="editEmail()"
          class="edit-btn"
          type="submit"
          value="Edit"
        />
        <input
          class="edit edit-email"
          id="update-email"
          placeholder="new email"
          hidden="true"
          type="text"
        />
      </div>
      <div class="header2" id="address-section">
        Address:
        <span class="info address" id="user-address"></span>
        <input
          @click="editAddress()"
          class="edit-btn"
          type="submit"
          value="Edit"
        />
        <input
          class="edit edit-address"
          id="update-address"
          placeholder="new address"
          hidden="true"
          type="text"
        />
      </div>
    </div>
    <br />
    <div>
      <span id="account-updating"> </span>
      <img
        hidden="true"
        id="loader"
        src="../images/loading-buffering.gif"
        alt=""
      />
      <input
        @click="saveInfo()"
        hidden="true"
        class="save"
        id="save-info"
        type="submit"
        value="Save"
      />
    </div>
  </div>
</template>


<script src="./account.js">
</script>

<style scoped>
.save {
  width: 300px;
  background-color: darkslategray;
  color: aliceblue;
  padding: 5px;
  border-radius: 10px;
  margin-left: 10px;
  font-size: 10px;
}

.save:hover {
  width: 300px;
  background-color: aliceblue;
  color: darkslategray;
  padding: 5px;
  border-radius: 10px;
  margin-left: 10px;
  font-size: 10px;
}

.edit {
  width: 200px;
  border-radius: 10px;
  font-size: 15px;
  margin-left: 10px;
}

.fadeIn {
  animation-name: fadeIn;
  animation-duration: 1s;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
}

.edit-account {
  float: right;
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

.edit-btn {
  background-color: darkslategrey;
  color: aliceblue;
  width: 50px;
  padding: 5px;
  border-radius: 10px;
  margin-left: 10px;
  font-size: 10px;
}

.edit-btn:hover {
  background-color: aliceblue;
  color: darkslategray;
  width: 50px;
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

.filter-input {
  border-radius: 6px;
  width: 200px;
  margin-left: 10px;
}

#account-updating {
  font-size: 20px;
  color: green;
  margin-left: 10px;
}

#loader {
  width: 4%;
  display: inline;
}
</style>
