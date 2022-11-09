<template>
  <div>
    <el-row class="row-head" type="flex" justify="start">
      <el-col align="start" style="margin-left: 20px">
        <el-button
          class="create-button"
          type="primary"
          icon="el-icon-plus"
          size="small"
          @click="gotoCreate"
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
            :id="item.id"
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
  </div>
</template>

<script>
import QuestionnaireCard from "./QuestionnaireCard"
import TypeChooseBox from "./TypeChooseBox"
import { Message } from "element-ui"
import * as survey from "@/api/survey"
import { getUser } from "@/utils/auth"
import { v4 } from "uuid"
import moment from "moment"
const randomName = Math.random().toString(36).slice(-6)
const newQuestionnaire = {
  userId: JSON.parse(getUser()).id,
  id: v4().replaceAll("-", ""), // 获取随机id
  title: `调查问卷_${randomName}`,
  status: "2",
  createTime: moment().format(),
  description: "一个新的问卷",
  fillCount: 0,
  talentId: JSON.parse(getUser()).owner,
  type: 1
}
export default {
  name: "Collection",
  components: { QuestionnaireCard, TypeChooseBox },
  data: function() {
    return {
      questionnaireList: [],
      checkedList: ["1", "2", "3"]
    }
  },
  mounted() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      survey.getQuestionnaires(1, 1, 1).then((response) => {
        this.questionnaireList = response.data.records
        console.log(this.questionnaireList)
      }).catch(() => {
        Message("error!问卷读取失败！")
      })
    },
    gotoCreate() {
      survey.addQuestionnaires(newQuestionnaire).then((res) => {
        console.log(res)
      })
      this.$router.push(`/create/index/${newQuestionnaire.id}`)
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
