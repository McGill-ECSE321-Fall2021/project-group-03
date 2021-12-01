<template>
  <div>
    <MenuBar />
    <div class="new-sched-combo">
      <div class="new-about-combo">
        <div class="whats-new">
          <div class="header">What's New</div>
          <div class="content">
            IT'S HOLIDAY SEASON!
            <br />
            Celebrate with your loved ones by cozying up and watching your
            favourite holiday movie (available here!).
            <br />
            Going on vacation for winter break? All your favourite beach reads
            are available to reserve now!
            <br />
            Check out our newest music albums! Set the ambiance for your holiday
            parties. Your guests' ears will be thanking you.
          </div>
        </div>
        <div class="about-us">
          <div class="header">About Us</div>
          <div class="content">
            The mission of the TASLA library is to bring people together and to
            provide an enriching environment for entertainment and education.
            Our team of incredible librarians are highly equipped to help
            clients fulfull their grandest of needs.
            <br />
            <br />
            <div class="header2">Meet Our Librarians</div>
            <img id="librarians" src="../images/librarians.jpg" alt="" />
          </div>
        </div>
      </div>
      <div class="library-hours">
        <div class="header">Library Hours</div>
        <div class="content" id="library-schedule">
          <table>
            <tr>
              <th>Time</th>
              <th>Date</th>
            </tr>
            <tr v-for="timeslot in librarySchedule" :key="timeslot">
              <td>{{ timeslot.startTime }} - {{ timeslot.endTime }}</td>
              <td>{{ timeslot.date }}</td>
            </tr>
          </table>
        </div>
      </div>
    </div>

    <div class="contact-information section">
      <div class="header">Contact Information</div>
      <div class="footer">
        <span class="header2">
          Address: <span class="info">1234 Python road</span>
        </span>
        <span class="header2">
          Telephone: <span class="info">(514)-123-4567</span>
        </span>
        <span class="header2">
          Email: <span class="info">library@gmail.com</span>
        </span>
      </div>
    </div>
  </div>
</template>

<script src="./home.js">
</script>
<style scoped>
.header {
  color: darkslategray;
  font-size: 35px;
  font-weight: 700;
  padding: 10px;
  text-align: center;
}

.header2 {
  text-align: center;
  font-size: 20px;
  font-weight: 600;
}

.info {
  font-weight: 400;
  color: black;
}

#librarians {
  width: 100%;
}

table {
  border-collapse: collapse;
  /* width: 40%; */
  margin-top: 0px;
  width: auto;
  margin-right: 105px;
  margin-left: auto;
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

table :first-child th {
  background-color: lightblue;
}

.new-sched-combo {
  display: flex;
}

.new-about-combo {
  width: 100%;
  margin-left: 30px;
}

.library-hours {
  width: 70%;
  justify-content: right;
  align-items: right;
}

.footer {
  display: flex;
  justify-content: space-around;
  color: teal;
  padding-bottom: 20px;
}
</style>
