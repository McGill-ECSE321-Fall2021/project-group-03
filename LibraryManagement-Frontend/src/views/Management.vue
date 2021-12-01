<template>
  <div>
    <MenuBar />

    <!-- ********** LIBRARIAN MANAGEMENT SECTION **********-->

    <button @click="expandLibrarianManagement()" class="collapsible">
      <span id="arrow-librarian-management">+</span> Librarian Management
    </button>

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
        placeholder="Librarian Username"
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

    <!-- ********** SET LIBRARY SCHEDULE SECTION **********-->

    <button @click="expandLibraryScheduling()" class="collapsible">
      <span id="arrow-library-scheduling">+</span> Library Scheduling
    </button>

    <div class="content" id="library-schedule">
      <div class="header">Set Library Schedule</div>
      <div class="lib-sched-inputs">
        <!-- ********** Date **********-->
        <div class="header2">Date</div>
        <input type="date" class="date" id="timeslot-date" name="birthday" />
        <span class="header2 success-msg" id="msg-date-library-schedule"></span>

        <!-- ********** Start Time **********-->
        <div class="header2">Start Time</div>
        <select class="title-info" id="library-schedule-start-hour" name="type">
          <option value="Start Time">Start Time</option>
          <option value="1">1:00</option>
          <option value="2">2:00</option>
          <option value="3">3:00</option>
          <option value="4">4:00</option>
          <option value="5">5:00</option>
          <option value="6">6:00</option>
          <option value="7">7:00</option>
          <option value="8">8:00</option>
          <option value="9">9:00</option>
          <option value="10">10:00</option>
          <option value="11">11:00</option>
          <option value="12">12:00</option>
        </select>
        <select
          class="title-info"
          id="library-schedule-start-hour-am-pm"
          name="type"
        >
          <option value="AM">AM</option>
          <option value="PM">PM</option>
        </select>
        <span
          class="header2 success-msg"
          id="msg-start-time-library-schedule"
        ></span>
        <!-- ********** End Time **********-->
        <div class="header2">End Time</div>
        <select class="title-info" id="library-schedule-end-hour" name="type">
          <option value="End Time">End Time</option>
          <option value="1">1:00</option>
          <option value="2">2:00</option>
          <option value="3">3:00</option>
          <option value="4">4:00</option>
          <option value="5">5:00</option>
          <option value="6">6:00</option>
          <option value="7">7:00</option>
          <option value="8">8:00</option>
          <option value="9">9:00</option>
          <option value="10">10:00</option>
          <option value="11">11:00</option>
          <option value="12">12:00</option>
        </select>
        <select
          class="title-info"
          id="library-schedule-end-hour-am-pm"
          name="type"
        >
          <option value="AM">AM</option>
          <option value="PM">PM</option>
        </select>
        <span
          class="header2 success-msg"
          id="msg-end-time-library-schedule"
        ></span>
      </div>

      <div>
        <input
          type="submit"
          @click="createLibraryScheduleTimeslot()"
          class="set-schedule"
          value="Set"
        />
        <span hidden=true class="header2 sucess-msg" id="loading">Loading <img src="../images/loading-buffering.gif" alt=""></span>
        <span
          class="header2 success-msg"
          id="msg-set-time-library-schedule"
        ></span>
        <span hidden="true" class="header2 success-msgSET"
          >Library Schedule Successfully Set.</span
        >
        <span hidden="true" class="header2 error-msgSET">Error message</span>
      </div>
    </div>

    <!-- ********** set staff schedule section **********-->

    <button @click="expandStaffScheduling()" class="collapsible">
      <span id="arrow-staff-scheduling">+</span> Staff Scheduling
    </button>

    <div class="content" id="staff-schedule">
      <div class="header">Set Staff Schedule</div>
      <div class="staff-sched-inputs">
        <div class="header2">Username</div>
        <input type="text" id="lib-username" placeholder="librarian username" />
         <span
          class="header2 success-msg"
          id="msg-lib-username-staff-schedule"
        ></span>

        <br />
         <!-- ********** Date **********-->
        <div class="header2">Date</div>
        <input type="date" class="date" id="timeslot-date-staff" name="birthday" />
        <span class="header2 success-msg" id="msg-date-staff-schedule"></span>

        <!-- ********** Start Time **********-->
        <div class="header2">Start Time</div>
        <select class="title-info" id="staff-schedule-start-hour" name="type">
          <option value="Start Time">Start Time</option>
          <option value="1">1:00</option>
          <option value="2">2:00</option>
          <option value="3">3:00</option>
          <option value="4">4:00</option>
          <option value="5">5:00</option>
          <option value="6">6:00</option>
          <option value="7">7:00</option>
          <option value="8">8:00</option>
          <option value="9">9:00</option>
          <option value="10">10:00</option>
          <option value="11">11:00</option>
          <option value="12">12:00</option>
        </select>
        <select
          class="title-info"
          id="staff-schedule-start-hour-am-pm"
          name="type"
        >
          <option value="AM or PM">A.M or P.M</option>
          <option value="AM">AM</option>
          <option value="PM">PM</option>
        </select>
        <span
          class="header2 success-msg"
          id="msg-start-time-staff-schedule"
        ></span>
        <!-- ********** End Time **********-->
        <div class="header2">End Time</div>
        <select class="title-info" id="staff-schedule-end-hour" name="type">
          <option value="End Time">End Time</option>
          <option value="1">1:00</option>
          <option value="2">2:00</option>
          <option value="3">3:00</option>
          <option value="4">4:00</option>
          <option value="5">5:00</option>
          <option value="6">6:00</option>
          <option value="7">7:00</option>
          <option value="8">8:00</option>
          <option value="9">9:00</option>
          <option value="10">10:00</option>
          <option value="11">11:00</option>
          <option value="12">12:00</option>
        </select>
        <select
          class="title-info"
          id="staff-schedule-end-hour-am-pm"
          name="type"
        >
          <option value="AM or PM">A.M or P.M</option>
          <option value="AM">AM</option>
          <option value="PM">PM</option>
        </select>
        <span
          class="header2 success-msg"
          id="msg-end-time-staff-schedule"
        ></span>
      </div>

      <div>
        <input
          type="submit"
          @click="createStaffScheduleTimeslot()"
          class="set-schedule"
          value="Set"
        />
        <span
          class="header2 success-msg"
          id="msg-set-time-staff-schedule"
        ></span>
        <span hidden="true" class="header2 success-msgSET"
          >Library Schedule Successfully Set.</span
        >
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
  padding-bottom: 0px;
}

.title-info {
  border-radius: 10px;
  padding: 10px;
}

.header2 {
  color: darkslategray;
  font-size: 20px;
  font-weight: 700;
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
  background-color: aliceblue;
  color: darkslategray;
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
  background-color: aliceblue;
  color: darkslategray;
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

#library-schedule-end-hour-am-pm {
  margin-left: 10px;
}

#library-schedule-start-hour-am-pm {
  margin-left: 10px;
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

img {
  width: 4%;
  display: inline;
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

#lib-username {
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 15px;
}

#staff-timeslot-date {
  margin-bottom: 15px;
}

#ts-staff {
  margin-bottom: 15px;
}

#timeslot-date {
  margin-bottom: 15px;
}

#library-schedule-start-hour {
  margin-bottom: 15px;
  padding: 10px;
  border-radius: 5px;
}

#library-schedule-start-hour-am-pm {
  padding: 10px;
  border-radius: 5px;
}

#library-schedule-end-hour {
  padding: 10px;
  border-radius: 5px;
}

#library-schedule-end-hour-am-pm {
  padding: 10px;
  border-radius: 5px;
}
</style>