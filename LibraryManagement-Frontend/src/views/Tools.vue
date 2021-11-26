<template>
  <div>
    <MenuBar />
    <div class="header">Create Title</div>
    <div class="content">
      <form action="">
        <input
          class="title-info"
          id="title-name"
          type="text"
          placeholder="Name"
        />
        <input
          class="title-info"
          id="title-description"
          type="text"
          placeholder="Description"
        />
        <input
          class="title-info"
          id="title-genre"
          type="text"
          placeholder="Genre"
        />
        <select class="title-info" id="title-type" name="type">
          <option value="Book">Book</option>
          <option value="Movie">Movie</option>
          <option value="MusicAlbum">Music Album</option>
          <option value="Newspaper">Newspaper</option>
          <option value="Archive">Archive</option>
        </select>
      </form>
      <br />
      <div>
        <input
          type="submit"
          @click="displaySuccess()"
          class="create-title"
          value="Create Title"
        />
        <span hidden="true" class="header2 success-msg"
          >Title successfully created!</span
        >
        <span hidden="true" class="header2 error-msg">Error message</span>
      </div>
    </div>
    <div class="header">Staff Schedule</div>
    <div class="content">SCHEDULE HERE</div>
  </div>
</template>
<script src="./tools.js">
</script>

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
</style>
