<template>
  <div>
    <MenuBar />
    <div class="whats-new section">
      <div class="header">What's New</div>
      <div class="content">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
        veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
        commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
        velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
        occaecat cupidatat non proident, sunt in culpa qui officia deserunt
        mollit anim id est laborum.
      </div>
    </div>
    <div class="library-hours section">
      <div class="header">Library Hours</div>
      <div class="content">
        <div class="monday header2">
          Today's Hours: <span class="monday-hours info">Closed</span>
        </div>
      </div>
    </div>
    <div class="about-us section">
      <div class="header">About Us</div>
      <div class="content">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
        veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
        commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
        velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
        occaecat cupidatat non proident, sunt in culpa qui officia deserunt
        mollit anim id est laborum.
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

<script>
import MenuBar from "../components/MenuBar.vue";
export default {
  name: "Home",
  components: {
    MenuBar,
  }
};
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
</style>
