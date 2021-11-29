<template>
<div>
    <MenuBar />
        <div class="header">Create Title</div>
    <div class="content">
      <form action="">
        <input
          class="title-info"
          id="title-name-create"
          type="text"
          placeholder="Name"
        />
        <input
          class="title-info"
          id="title-description-create"
          type="text"
          placeholder="Description"
        />
        <select class="title-info" id="title-genre-create" name="type">
          <option value="None">None</option>
          <option value="Adventure">Adventure</option>
          <option value="Action">Action</option>
          <option value="Comedy">Comedy</option>
          <option value="Horror">Horror</option>
        </select>
        <select class="title-info" id="title-type-create" name="type">
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
        <span hidden="true" class="header2 success-msg"
          >Title successfully created!</span
        >
        <span hidden="true" class="header2 error-msg">Error message</span>
      </div>
    </div>
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
        <option value="None">None</option>
        <option value="Adventure">Adventure</option>
        <option value="Action">Action</option>
        <option value="Comedy">Comedy</option>
        <option value="Horror">Horror</option>
      </select>
      <select class="title-info" id="title-type-update" name="type1">
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
    </div>
    <div class="header">Remove Title</div>
    <div>
      <input
        class="title-info"
        id="title-id-delete"
        type="text"
        placeholder="Id"
      />
      <br />
      <input
        type="submit"
        @click="deleteTitle()"
        class="create-title"
        value="Delete Title"
      />
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
