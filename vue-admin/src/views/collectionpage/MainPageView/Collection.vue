<template>
  <div>
    <el-row class="row-head" type="flex" justify="start">
      <el-col align="start">
        <el-button
          class="create-button"
          type="primary"
          icon="el-icon-plus"
          size="small"
          @click="dialogTableVisible = true"
        >创建问卷
        </el-button>
      </el-col>
      <el-col align="end" class="hidden-xs-only">
        <TypeChooseBox
          @changeShow="changeShow"
        />
      </el-col>
    </el-row>

    <el-divider />

    <div class="questionnaire-card-ground">
      <el-row :gutter="10">
        <el-col
          v-for="item in questionnaireList"
          :key="item.questionnaireId"
          :xs="12"
          :sm="6"
          :md="6"
          :lg="3"
          :xl="2"
        >
          <QuestionnaireCard
            v-if="checkedList.indexOf(item.status)!==-1"
            :id="item.questionnaireId"
            class="questionnaire-card"
            :title="item.title"
            :status="item.status"
            :create-time="item.createTime"
            :end-time="item.endTime"
            :description="item.description"
            :fill-count="item.fillCount"
            @deleteQuestionnaire="fetchData"
          />
        </el-col>
      </el-row>
    </div>
    <el-dialog title="创建方式" :visible.sync="dialogTableVisible">
      <ElRow
        type="flex"
        style="flex-direction: row;justify-content: space-around;"
      >
        <ElCol span="7">
          <ElCard>
            <div slot="header" class="clearfix">
              <el-button type="primary" @click="gotoCreate">从模板传建</el-button>
            </div>
          </ElCard>
        </ElCol>
        <ElCol span="7">
          <ElCard>
            <div slot="header" class="clearfix">
              <el-button type="primary" @click="gotoCreate">从问题库创建</el-button>            </div>
          </ElCard>
        </ElCol>
        <ElCol span="7">
          <ElCard>
            <div slot="header" class="clearfix">
              <el-button type="primary" @click="gotoCreate">自定义</el-button>            </div>
          </ElCard>
        </ElCol>
      </ElRow>
    </el-dialog>
  </div>
</template>

<script>
import QuestionnaireCard from "./QuestionnaireCard"
import TypeChooseBox from "./TypeChooseBox"
import { ServerUrl } from "@/config"
import store from "@/store"

export default {
  name: "Collection",
  components: { QuestionnaireCard, TypeChooseBox },
  data: function() {
    return {
      questionnaireList: [],
      checkedList: ["collecting", "editing", "closed"],
      gridData: [{
        date: "2016-05-02",
        name: "王小虎",
        address: "上海市普陀区金沙江路 1518 弄"
      }, {
        date: "2016-05-04",
        name: "王小虎",
        address: "上海市普陀区金沙江路 1518 弄"
      }, {
        date: "2016-05-01",
        name: "王小虎",
        address: "上海市普陀区金沙江路 1518 弄"
      }, {
        date: "2016-05-03",
        name: "王小虎",
        address: "上海市普陀区金沙江路 1518 弄"
      }],
      dialogTableVisible: false
    }
  },
  mounted() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.axios.get(ServerUrl + `/api/getQuestionnaires?username=${store.getters.name}`).then((response) => {
        this.questionnaireList = response.data["questionnaires"]
      }).catch(() => {
        this.$message({ message: "error!问卷读取失败！", duration: 1000 })
      })
    },
    gotoCreate() {
      this.axios.get(ServerUrl + `/api/createQuestionnaire?username=${store.getters.name}`).then((response) => {
        this.$router.push("/create/index/" + response.data["id"])
      })
    },
    changeShow(data) {
      this.checkedList = data
    }
  }
}
</script>

<style scoped>
    .row-head {
        margin-top: 15px;
        margin-left: 5px;
    }

</style>
