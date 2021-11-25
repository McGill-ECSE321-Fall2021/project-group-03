<template>
  <div>
    <MenuBar />
    <div class="header">{{ $route.params.name }}</div>
    <div class="content">
      <div>
        Type:
        <span class="info">Party</span>
      </div>
      <div>
        Capacity:
        <span class="info">40</span>
      </div>
    </div>
    <span class="header-2">Available time slots for:</span>
    <input type="date" id="birthday" name="birthday" />
    <AvailableRoomsTable />
    <input class="submit-btn" type="submit" value="Reserve" />
  </div>
</template>
<script>
import MenuBar from "../components/MenuBar.vue";
import AvailableRoomsTable from "../components/AvailableRoomsTable.vue";
export default {
  name: "Home",
  components: {
    MenuBar,
    AvailableRoomsTable,
  },
};
</script>
<style>
.header {
  /* text-align: center; */
  color: darkslategray;
  font-size: 35px;
  font-weight: 700;
  padding: 20px;
  padding-bottom: 0px;
  padding-top: 0px;
}

.content {
  padding-left: 20px;
  color: darkslategray;
  font-size: 15px;
  font-weight: 400;
}

.header-2 {
  padding-left: 20px;
  color: darkslategray;
  font-size: 20px;
  font-weight: 450;
}

#birthday {
  margin-top: 15px;
  margin-bottom: 15px;
}

.submit-btn {
  margin-left: 80px;
  margin-bottom: 20px;
}
</style>