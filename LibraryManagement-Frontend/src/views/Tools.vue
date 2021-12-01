<template>
  <div>
    <MenuBar />

    <!-- ********** INVENTORY MANAGEMENT SECTION **********-->

    <button @click="expandInventory()" class="collapsible">
      <span id="arrow-inventory">+</span> Inventory Management
    </button>
    <div id="inventory" class="content">
      <!-- ********** create title section **********-->
      <div class="header">Create Title</div>
      <div>
        <form action="">
          <input
            class="title-info"
            id="title-name-create"
            type="text"
            placeholder="Name"
          />
          <input class="title-info" type="text" id="title-description-create" placeholder="Description" />
          <select class="title-info" id="title-genre-create" name="type">
            <option value="Genre">Genre</option>
            <option value="Adventure">Adventure</option>
            <option value="Action">Action</option>
            <option value="Comedy">Comedy</option>
            <option value="Horror">Horror</option>
          </select>
          <select class="title-info" id="title-type-create" name="type">
            <option value="Type">Type</option>
            <option value="Book">Book</option>
            <option value="Movie">Movie</option>
            <option value="MusicAlbum">Music Album</option>
            <option value="Newspaper">Newspaper</option>
            <option value="Archive">Archive</option>
          </select>
        </form>
        <div>
          <input
            type="submit"
            @click="createTitle()"
            class="create-title"
            value="Create Title"
          />
          <span class="header2 success-msg" id="msg-create-title"></span>
          <span hidden="true" class="header2 error-msg"></span>
        </div>
      </div>
      <!-- ********** update title section **********-->
      <div class="header">Update Title</div>
      <div>
        <input
          class="title-info"
          id="title-name-update"
          type="text"
          placeholder="Name"
        />
        <input
          class="title-info"
          id="title-description-update"
          type="text"
          placeholder="Description"
        />
        <select class="title-info" id="title-genre-update" name="type">
          <option value="Genre">Genre</option>
          <option value="Adventure">Adventure</option>
          <option value="Action">Action</option>
          <option value="Comedy">Comedy</option>
          <option value="Horror">Horror</option>
        </select>
        <select class="title-info" id="title-type-update" name="type1">
          <option value="Type">Type</option>
          <option value="Book">Book</option>
          <option value="Movie">Movie</option>
          <option value="MusicAlbum">Music Album</option>
          <option value="Newspaper">Newspaper</option>
          <option value="Archive">Archive</option>
        </select>
        <br />
        <input
          type="submit"
          @click="updateTitle()"
          class="create-title"
          value="Update Title"
        />
        <span class="header2 success-msg" id="msg-update-title"></span>
        <span hidden="true" class="header2 error-msg">Error message</span>
      </div>
      <!-- ********** remove title section **********-->
      <div class="header">Remove Title</div>
      <div>
        <input
          class="title-info"
          id="title-id-delete"
          type="text"
          placeholder="Title Id"
        />
        <br />
        <input
          type="submit"
          @click="deleteTitle()"
          class="create-title"
          value="Delete Title"
        />
        <span class="header2 success-msg" id="msg-delete-title"></span>
        <span hidden="true" class="header2 error-msg">Error message</span>
      </div>
    </div>
    <!-- ********** Staff schedule SECTION **********-->
    <button @click="expandStaffSchedule()" class="collapsible">
      <span id="arrow-staff">+</span> Staff Schedule
    </button>
    <div id="staff-schedule" class="content">
      <div class="header">Staff Schedule</div>
      <table>
        <tr>
          <th>Time</th>
          <th>Date</th>
        </tr>
        <tr v-for="timeslot in this.staffSchedule" :key="timeslot">
          <td>{{ timeslot.startTime }} - {{ timeslot.endTime }}</td>
          <td>{{ timeslot.date }}</td>
        </tr>
      </table>
    </div>

    <!-- ********** Checkout title SECTION **********-->
    <button @click="expandCheckout()" class="collapsible">
      <span id="arrow-checkout">+</span> Checkout Title
    </button>
    <div id="checkout-title" class="content">
      <div class="header">Checkout Title</div>
      <input
        class="title-info"
        id="client-username"
        type="text"
        placeholder="Client Username"
      />
      <br />
      <input
        class="title-info"
        id="title-name"
        type="text"
        placeholder="Title Name"
      />
      <br />
      <input
        type="submit"
        @click="checkoutTitle()"
        class="create-title"
        value="Checkout"
      />
      <div id="msg-checkout"></div>
    </div>
  </div>
</template>
<script src="./tools.js"></script>
<style scoped>
.header {
  color: darkslategray;
  font-size: 35px;
  font-weight: 700;
  padding: 10px;
}
.create-title {
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
  background-color: darkslategray;
  color: aliceblue;
  width: 200px;
}
.fadeIn {
  animation-name: fadeIn;
  animation-duration: 0.5s;
}
.error-msg.header2 {
  color: crimson;
  padding-left: 15px;
}
.success-msg.header2 {
  padding-left: 15px;
  color: forestgreen;
}
@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
.create-title:hover {
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
  background-color: aliceblue;
  color: darkslategray;
  width: 200px;
}
.title-info {
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
}
.title-type {
  width: 200px;
  padding: 13px;
}
.header2 {
  color: darkslategray;
  font-weight: 700;
  padding-bottom: 10px;
  padding: 10px;
}
.collapsible {
  margin-top: 20px;
  background-color: cadetblue;
  color: aliceblue;
  border: none;
  text-align: left;
  outline: none;
  width: 95%;
  padding: 10px;
  margin-left: 20px;
  margin-right: 20px;
}
.collapsible:hover {
  background-color: rgb(63, 102, 105);
}
.content {
  overflow: hidden;
  padding: 0 10px;
  display: none;
  background-color: rgb(227, 243, 243);
  width: 95%;
  margin-left: 20px;
  margin-right: 20px;
}

#msg-checkout {
  padding: 10px;
}

table {
  border-collapse: collapse;
  width: 40%;
  margin-top: 0px;
  margin-left: auto;
  margin-right: auto;
}

td,
th {
  border: 1px solid aliceblue;
  text-align: center;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: aliceblue;
}

table {
  margin-bottom: 20px;
}

table :first-child th {
  background-color: lightblue;
}

</style>
