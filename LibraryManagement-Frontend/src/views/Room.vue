<template>
  <div>
    <MenuBar />
    <div class="header">Room Finder</div>
    <div class="header-2">Available Rooms</div>
    <div class="type-selector">
      <label for="room-type">Room Type: </label>
      <select name="room-type">
        <option value="all">All</option>
        <option value="study">Study</option>
        <option value="party">Party</option>
      </select>
    </div>
    <input type="date" id="birthday" name="birthday" />
    <div class="header-3">Click on room name to reserve</div>

    <div class="content titles">
      <RoomsTable />
    </div>
  </div>
</template>

<script>
import MenuBar from "../components/MenuBar.vue";
import RoomsTable from "../components/RoomsTable.vue";

export default {
  name: "Home",
  components: {
    MenuBar,
    RoomsTable,
  },
};
</script>

<style scoped>
.header {
  color: darkslategray;
  font-size: 35px;
  font-weight: 700;
  padding: 10px;
  text-align: center;
}

.header-2 {
  padding-top: 20px;
  padding-left: 20px;
  color: darkslategray;
  font-size: 25px;
  font-weight: 500;
}

.header-3 {
  padding-left: 20px;
  color: darkslategray;
  font-size: 15px;
  font-weight: 350;
}

/* FIX THIS */
.type-selector {
  display: inline;
  padding-left: 20px;
}

#birthday {
  margin-left: 18px;
  margin-bottom: 15px;
}
</style>