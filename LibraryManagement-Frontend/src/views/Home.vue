<template>
  <div>
    <MenuBar />
    <div class="whats-new section">
      <div class="header">What's New</div>
      <div class="content">
        IT'S HOLIDAY SEASON!
        <br />
        <br />
        Celebrate with your loved ones by cozying up and watching your favourite
        holiday movie (available here!).
        <br />
        <br />
        Going on vacation for winter break? All your favourite beach reads are
        available to reserve now!
        <br />
        <br />
        Check out our newest music albums! Set the ambiance for your holiday
        parties. Your guests' ears will be thanking you.
      </div>
    </div>
    <div class="library-hours section">
      <div class="header">Library Hours</div>
      <div class="content" id="library-schedule">
        
      </div>
    </div>
    <div class="about-us section">
      <div class="header">About Us</div>
      <div class="content">
        The mission of the TASLA library is to bring people together and to
        provide an enriching environment for entertainment and education. Our
        team of incredible librarians are highly equipped to help clients
        fulfull their grandest of needs.
        <br />
        <br />
        <div class="header2">Meet Our Librarians</div>
        <img id="librarians" src="../images/librarians.jpg" alt="" />
      </div>
    </div>
    <div class="contact-information section">
      <div class="header">Contact Information</div>
      <div class="content">
        <div class="header2">
          Address: <span class="info">1234 Python road</span>
        </div>
        <div class="header2">
          Telephone: <span class="info">(514)-123-4567</span>
        </div>
        <div class="header2">
          Email: <span class="info">library@gmail.com</span>
        </div>
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
}

.header2 {
  color: darkslategray;
  font-weight: 700;
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

#librarians {
  width: 50%;
}
</style>
