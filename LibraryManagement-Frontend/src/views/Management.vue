<template>
  <div>
    <MenuBar />

    <!-- ********** LIBRARIAN MANAGEMENT SECTION **********-->

    <button @click="expandLibrarianManagement()" class="collapsible"><span id="arrow-librarian-management">+</span> Librarian Management</button>

    <div class="content" id="librarian-management">

    <!-- ********** hire librarian section **********-->

    <div class="header">Hire Librarian</div>
    <div>
    <input
      class="librarian-info"
      id="librarian-hire-username"
      type="text"
      placeholder="Username"
    />
    <input
      class="librarian-info"
      id="librarian-hire-password"
      type="text"
      placeholder="Password"
    />
    <input
      class="librarian-info"
      id="librarian-hire-fullname"
      type="text"
      placeholder="Full Name"
    />

    <div>
      <input
        type="submit"
        @click="hireLibrarian()"
        class="add-librarian"
        value="Hire"
      />
      <span hidden="true" class="header2 success-msgH"
        >Librarian Successfully Hired!</span
      >
      <span class="header2 success-msg" id="msg-create-librarian"></span>
      <span hidden="true" class="header2 error-msgH">Error message</span>
    </div>
    </div>

    <!-- ********** fire librarian section **********-->

    <div class="header">Fire Librarian</div>

    <input
      class="librarian-info"
      type="text"
      placeholder="Librarian Id"
      id="librarian-fire-username"
    />
    <div>
      <input
        type="submit"
        @click="fireLibrarian()"
        class="delete-librarian"
        value="Fire"
      />
      <span hidden="true" class="header2 success-msgF"
        >Librarian Successfully Fired.</span
      >
      <span class="header2 success-msg" id="msg-fire-librarian"></span>
      <span hidden="true" class="header2 error-msgF">Error message</span>
    </div>
  </div>


  <!-- ********** set library schedule section **********-->

  <button @click="expandLibraryScheduling()" class="collapsible"><span id="arrow-library-scheduling">+</span> Library Scheduling</button>

  <div class="content" id="library-schedule">
    <div class="header">Set Library Schedule</div>
    <div class="lib-sched-inputs">
      <div class="header2">Date</div>
      <input type="date" class="date" id="timeslot-date" name="birthday" />
      <div class="header2">Start Time</div>
      <input id="ts" type="time" min="09:00" max="22:00" required />
      <div class="header2">End Time</div>
      <input id="te" type="time" min="09:00" max="22:00" required />
    </div>

    <div>
      <input
        type="submit"
        @click="createLibraryScheduleTimeslot()"
        class="set-schedule"
        value="Set"
      />
      <span hidden="true" class="header2 success-msgSET">Library Schedule Successfully Set.</span>
      <span hidden="true" class="header2 error-msgSET">Error message</span>
    </div>
    </div>

  <!-- ********** set staff schedule section **********-->

  <button @click="expandStaffScheduling()" class="collapsible"><span id="arrow-staff-scheduling">+</span> Staff Scheduling</button>

  <div class="content" id="staff-schedule">
    <div class="header">Set Staff Schedule</div>
    <div class="lib-sched-inputs">
      <div class="header2">Username</div>
      <input type="text" id="lib-username" placeholder="librarian username">
      <br>
      <div class="header2">Date</div>
      <input type="date" class="date" id="staff-timeslot-date" name="birthday" />
      <div class="header2">Start Time</div>
      <input id="ts-staff" type="time" min="09:00" max="22:00" required />
      <div class="header2">End Time</div>
      <input id="te-staff" type="time" min="09:00" max="22:00" required />
    </div>

    <div>
      <input
        type="submit"
        @click="createStaffScheduleTimeslot()"
        class="set-schedule"
        value="Set"
      />
      <span hidden="true" class="header2 success-msgSET">Library Schedule Successfully Set.</span>
      <span hidden="true" class="header2 error-msgSET">Error message</span>
    </div>
    </div>
  </div>
</template>
<script src="./management.js">

</script>

<style>
.header {
  color: darkslategray;
  font-size: 35px;
  font-weight: 700;
  padding: 10px;
}

.header2 {
  color: darkslategray;
  font-size: 20px;
  font-weight: 700;
  padding: 10px;
}

input[type="time"] {
  color: darkslategray;
  font-size: 18px;
  font-weight: 700;
  padding: 10px;
  border-radius: 10px;
}
.librarian-info {
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
}

.add-librarian {
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
  background-color: darkslategray;
  color: aliceblue;
  width: 200px;
}

.add-librarian:hover {
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
  background-color: aliceblue;
  color: darkslategray;
  width: 200px;
}

.delete-librarian {
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
  background-color: darkslategray;
  color: aliceblue;
  width: 200px;
}

.delete-librarian:hover {
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
  background-color: aliceblue;
  color: darkslategray;
  width: 200px;
}

.fadeIn {
  animation-name: fadeIn;
  animation-duration: 0.5s;
}

.error-msgH.header2 {
  color: crimson;
  padding-left: 15px;
}

.success-msgH.header2 {
  padding-left: 15px;
  color: forestgreen;
}

.error-msgF.header2 {
  color: crimson;
  padding-left: 15px;
}

.success-msgF.header2 {
  padding-left: 15px;
  color: forestgreen;
}

.error-msgSET.header2 {
  color: crimson;
  padding-left: 15px;
}

.success-msgSET.header2 {
  padding-left: 15px;
  color: forestgreen;
}

.set-schedule {
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
  background-color: darkslategray;
  color: aliceblue;
  width: 200px;
}

.set-schedule:hover {
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
  background-color: aliceblue;
  color: darkslategray;
  width: 200px;
}

.lib-sched-inputs {
  padding: 10px;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.date {
  border-radius: 10px;
  padding: 10px;
}

.collapsible{
  margin-top: 10px;
  background-color: cadetblue;
  color:aliceblue;
  border: none;
  text-align: left;
  outline: none;
  width: 100%; 
  padding: 10px;
}
.collapsible:hover{
  margin-top: 10px;
  background-color: rgb(63, 102, 105);
  color:aliceblue;
  border: none;
  text-align: left;
  outline: none;
  width: 100%; 
  padding: 10px;
}

.content{
  overflow: hidden;
  padding: 0 10px;
  display: none;
  background-color: rgb(227, 243, 243);
}

#lib-username{
  border-radius: 10px;
  padding: 10px;
}

</style>