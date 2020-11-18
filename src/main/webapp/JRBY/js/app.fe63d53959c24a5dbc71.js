webpackJsonp([1],{"4ml/":function(e,t){},KF2q:function(e,t){},NHnr:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a("7+uW"),n={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},staticRenderFns:[]};var s=a("VU/8")({name:"App"},n,!1,function(e){a("KF2q")},null,null).exports,o=a("/ocq"),i=a("Fd2+");r.default.use(i.d),r.default.use(i.c);var l={name:"JobBooking",data:function(){return{number:"",scannerNumber:"",transferCardNumber:"",materialNumber:"",flag:!0,processNumber:"",processID:"",orderNumber:"",inputNumber:"",outputNumber:"",badID:"",badProject:"",showProcessPicker:!1,showBadPicker:!1,unitTime:1,hourlyWage:1,charges:0,processes:[{processID:"12",text:"工序1"},{processID:"13",text:"工序2"},{processID:"14",text:"工序3"},{processID:"15",text:"工序4"},{processID:"16",text:"工序5"}],bads:[{BadID:"12",text:"工序1"},{BadID:"13",text:"工序2"},{BadID:"14",text:"工序3"},{BadID:"15",text:"工序4"},{BadID:"16",text:"工序5"}]}},components:{},created:function(){this.getAllBadDes()},mounted:function(){window.scannerSearch=this.scannerSearch},computed:{badNumber:function(){return(this.inputNumber-this.outputNumber).toFixed(0)<=0&&(this.badProject="",this.badID=""),(this.inputNumber-this.outputNumber).toFixed(0)},taskTime:function(){return(this.outputNumber/this.unitTime).toFixed(2)},badProjectshow:function(){return this.badNumber>0},charges:function(){return(this.outputNumber/this.unitTime*this.hourlyWage).toFixed(2)}},watch:{scannerNumber:{deep:!0,handler:function(e,t){this.number=e,this.onSearch()}}},methods:{onSearch:function(){var e=this;android.showLodingDialog("正在获取工序流转卡数据..."),this.$api.get("/selectProcessBooking",{Number:this.number},function(t){android.closeLodingDialog(),e.clear(),t.status>=200&&t.status<300?0==t.data.msg.transferCardNumber.length?i.e.fail("工序流转单不存在,请确认工序流转卡编码"):0==t.data.msg.processes.length?i.e.fail("当前工单已报工完毕"):(i.e.success("获取工序流转单成功"),e.transferCardNumber=t.data.msg.transferCardNumber,e.orderNumber=t.data.msg.orderNumber,e.materialNumber=t.data.msg.materialNumber,e.onProcessConfirm(t.data.msg.processes),e.hourlyWage=t.data.msg.hourlyWage,e.bads=t.data.msg.badDec,e.flag=!1):i.e.fail("获取工序流转单时出现异常，请重新操作")})},onProcessConfirm:function(e){this.processNumber=e.text,this.processID=e.processID,this.unitTime=e.unitTime},onBadConfirm:function(e){this.badID=e.badID,this.badProject=e.text,this.showBadPicker=!1},clear:function(){this.transferCardNumber="",this.processNumber="",this.processID="",this.inputNumber="",this.outputNumber="",this.badProject="",this.badID="",this.materialNumber=""},submit:function(){var e=this;this.badNumber<0?i.e.fail("产出数量不得大于投入数量"):this.badNumber>0&&""==this.badID?i.e.fail("存在不良，请填写不良项目"):this.processID.length>0&&this.inputNumber.length>0&&this.outputNumber.length>0&&this.taskTime.length>0?i.a.confirm({title:"确认",message:"请确认当前数据已填写正确"}).then(function(){android.showLodingDialog("正在提交工序流转卡数据...");var t={transferCardNumber:e.transferCardNumber,processID:e.processID,processNumber:e.processNumber,inputNumber:e.inputNumber,outputNumber:e.outputNumber,taskTime:e.taskTime,badID:e.badID,badNumber:e.badNumber,badProject:e.badProject,userID:android.getUserID(),orderNumber:e.orderNumber};e.$api.get("/submitProcessBooking",{submitData:t},function(e){android.closeLodingDialog(),e.status>=200&&e.status<300?"true"==e.data.success?i.a.alert({message:e.data.msg}).then(function(){android.back()}):i.e.fail(e.data.msg):i.e.fail("数据提交出现异常,请重新提交")})}).catch(function(){}):i.e.fail("请填写必填项")},getAllBadDes:function(){var e=this;android.showLodingDialog("正在加载基础数据..."),this.$api.get("/selectAllBadDes",{},function(t){android.closeLodingDialog(),t.status>=200&&t.status<300&&"true"==t.data.success?e.bads=t.data.msg:android.alertDialog("警告","获取不良项目出现异常，请重新打开")})},openScanner:function(){android.openScanner()},scannerSearch:function(e){this.scannerNumber=e}}},u={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("Div",{staticStyle:{padding:"0.2rem"}},[a("van-search",{staticStyle:{"margin-right":"1rem"},attrs:{placeholder:"请输入工序流转卡编码","show-action":"",shape:"round",clear:"","right-icon":""},on:{search:e.onSearch},model:{value:e.number,callback:function(t){e.number=t},expression:"number"}},[a("div",{attrs:{slot:"action",disabled:"loading"},on:{click:e.onSearch},slot:"action"},[e._v("搜索")]),e._v(" "),a("div",{attrs:{slot:"right-icon"},on:{click:e.openScanner},slot:"right-icon"},[a("van-icon",{staticClass:"iconfont",attrs:{"class-prefix":"icon",name:"saomiao"}})],1)]),e._v(" "),a("van-cell-group",[a("van-field",{attrs:{label:"流转卡编码：",disabled:"","label-align":"left"},model:{value:e.transferCardNumber,callback:function(t){e.transferCardNumber=t},expression:"transferCardNumber"}}),e._v(" "),a("van-field",{attrs:{label:"型号：",disabled:"","label-align":"left"},model:{value:e.materialNumber,callback:function(t){e.materialNumber=t},expression:"materialNumber"}}),e._v(" "),a("van-field",{attrs:{label:"订单号：",disabled:"","label-align":"left"},model:{value:e.orderNumber,callback:function(t){e.orderNumber=t},expression:"orderNumber"}}),e._v(" "),a("van-field",{attrs:{readonly:"",disabled:"",label:"工序号：","label-align":"left",value:e.processNumber}}),e._v(" "),a("van-field",{attrs:{required:"",disabled:e.flag,label:"投入数：","label-align":"left",type:"number"},model:{value:e.inputNumber,callback:function(t){e.inputNumber=t},expression:"inputNumber"}}),e._v(" "),a("van-field",{attrs:{required:"",disabled:e.flag,label:"产出数：","label-align":"left",type:"number"},model:{value:e.outputNumber,callback:function(t){e.outputNumber=t},expression:"outputNumber"}}),e._v(" "),a("van-field",{attrs:{label:"不良数：","label-align":"left",disabled:"",type:"number"},model:{value:e.badNumber,callback:function(t){e.badNumber=t},expression:"badNumber"}}),e._v(" "),a("van-field",{attrs:{disabled:"",label:"工时：","label-align":"left",type:"number"},model:{value:e.taskTime,callback:function(t){e.taskTime=t},expression:"taskTime"}}),e._v(" "),a("van-field",{attrs:{disabled:"",label:"加工费：","label-align":"left",type:"number"},model:{value:e.charges,callback:function(t){e.charges=t},expression:"charges"}}),e._v(" "),0==e.flag&&1==e.badProjectshow?a("van-field",{attrs:{readonly:"",clickable:"",label:"不良项目：","label-align":"left",value:e.badProject,placeholder:"选择不良项目"},on:{click:function(t){e.showBadPicker=!0}}}):a("van-field",{attrs:{readonly:"",clickable:"",label:"不良项目：","label-align":"left",value:e.badProject,placeholder:"选择不良项目"}}),e._v(" "),a("van-popup",{attrs:{position:"bottom"},model:{value:e.showBadPicker,callback:function(t){e.showBadPicker=t},expression:"showBadPicker"}},[a("van-picker",{attrs:{"show-toolbar":"",columns:e.bads},on:{cancel:function(t){e.showBadPicker=!1},confirm:e.onBadConfirm}})],1)],1),e._v(" "),a("div",{staticStyle:{"margin-top":"1rem"}},[a("van-row",[a("van-col",{attrs:{span:"6"}}),e._v(" "),a("van-col",{attrs:{span:"6",align:"center"}},[a("van-button",{staticClass:"button",attrs:{round:"",size:"small"},on:{click:e.clear}},[e._v("清空")])],1),e._v(" "),a("van-col",{attrs:{span:"6",align:"center"}},[a("van-button",{staticClass:"button",attrs:{round:"",size:"small",type:"danger",disabled:e.flag},on:{click:e.submit}},[e._v("提交")])],1),e._v(" "),a("van-col",{attrs:{span:"6"}})],1)],1)],1)},staticRenderFns:[]};var c=a("VU/8")(l,u,!1,function(e){a("uxxp")},"data-v-1e988d05",null).exports,d={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"hello"},[a("h1",[e._v(e._s(e.msg))]),e._v(" "),a("h2",[e._v("Essential Links")]),e._v(" "),e._m(0),e._v(" "),a("h2",[e._v("Ecosystem")]),e._v(" "),e._m(1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("ul",[a("li",[a("a",{attrs:{href:"https://vuejs.org",target:"_blank"}},[e._v("\n        Core Docs\n      ")])]),e._v(" "),a("li",[a("a",{attrs:{href:"https://forum.vuejs.org",target:"_blank"}},[e._v("\n        Forum\n      ")])]),e._v(" "),a("li",[a("a",{attrs:{href:"https://chat.vuejs.org",target:"_blank"}},[e._v("\n        Community Chat\n      ")])]),e._v(" "),a("li",[a("a",{attrs:{href:"https://twitter.com/vuejs",target:"_blank"}},[e._v("\n        Twitter\n      ")])]),e._v(" "),a("br"),e._v(" "),a("li",[a("a",{attrs:{href:"http://vuejs-templates.github.io/webpack/",target:"_blank"}},[e._v("\n        Docs for This Template\n      ")])])])},function(){var e=this.$createElement,t=this._self._c||e;return t("ul",[t("li",[t("a",{attrs:{href:"http://router.vuejs.org/",target:"_blank"}},[this._v("\n        vue-router\n      ")])]),this._v(" "),t("li",[t("a",{attrs:{href:"http://vuex.vuejs.org/",target:"_blank"}},[this._v("\n        vuex\n      ")])]),this._v(" "),t("li",[t("a",{attrs:{href:"http://vue-loader.vuejs.org/",target:"_blank"}},[this._v("\n        vue-loader\n      ")])]),this._v(" "),t("li",[t("a",{attrs:{href:"https://github.com/vuejs/awesome-vue",target:"_blank"}},[this._v("\n        awesome-vue\n      ")])])])}]};var m=a("VU/8")({name:"HelloWorld",data:function(){return{msg:"Welcome to Your Vue.js App"}}},d,!1,function(e){a("UEbX")},"data-v-d8ec41bc",null).exports,b={name:"RevisePassword",data:function(){return{password:"",password_comfirm:""}},methods:{clear:function(){this.password="",this.password_comfirm=""},submit:function(){0==this.password_comfirm.length||0==this.password.length?i.e.fail("填写密码不得为空"):this.password!=this.password_comfirm?i.e.fail("填写密码不一致"):(android.showLodingDialog("正在修改账号密码..."),this.$api.get("/revisePassword",{staffID:android.getUserID(),staffPassword:this.password},function(e){android.closeLodingDialog(),e.status>=200&&e.status<300?(android.toastLog(e.data.msg),android.back()):i.e.fail("重置密码出现异常")}))}},computed:{}},p={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticStyle:{padding:"0.2rem"}},[a("van-field",{attrs:{type:"password",placeholder:"请输入登录密码",clearable:"","label-width":"3.5rem"},model:{value:e.password,callback:function(t){e.password=t},expression:"password"}}),e._v(" "),a("van-field",{attrs:{type:"password",placeholder:"请再次输入登录密码",clearable:"","label-width":"3.5rem"},model:{value:e.password_comfirm,callback:function(t){e.password_comfirm=t},expression:"password_comfirm"}}),e._v(" "),a("van-row",{staticStyle:{"margin-top":"1rem"}},[a("van-col",{attrs:{span:"6"}}),e._v(" "),a("van-col",{attrs:{span:"6",align:"center"}},[a("van-button",{staticClass:"button",attrs:{round:"",size:"small"},on:{click:e.clear}},[e._v("清空")])],1),e._v(" "),a("van-col",{attrs:{span:"6",align:"center"}},[a("van-button",{staticClass:"button",attrs:{round:"",size:"small",type:"danger"},on:{click:e.submit}},[e._v("提交")])],1),e._v(" "),a("van-col",{attrs:{span:"6"}})],1)],1)},staticRenderFns:[]},f=a("VU/8")(b,p,!1,null,null,null).exports,h={data:function(){return{list:[],loading:!1,finished:!1}},methods:{onLoad:function(){var e=this;setTimeout(function(){for(var t=0;t<10;t++)e.list.push(e.list.length+1);e.loading=!1,e.list.length>=40&&(e.finished=!0)},1e3)}}},v={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("van-list",{attrs:{finished:e.finished,"finished-text":"没有更多了"},on:{load:e.onLoad},model:{value:e.loading,callback:function(t){e.loading=t},expression:"loading"}},[a("van-row",{attrs:{type:"flex",justify:"center"}},[a("van-col",{attrs:{span:"6"}},[e._v("span: 6")]),e._v(" "),a("van-col",{attrs:{span:"6"}},[e._v("span: 6")]),e._v(" "),a("van-col",{attrs:{span:"6"}},[e._v("span: 6")])],1)],1)},staticRenderFns:[]},g=a("VU/8")(h,v,!1,null,null,null).exports;r.default.use(o.a);var _=new o.a({routes:[{path:"/jobBooking",name:"JobBooking",component:c},{path:"/helloWorld",name:"HelloWorld",component:m},{path:"/revisePassword",name:"RevisePassword",component:f},{path:"/inventory",name:"inventory",component:g}]}),N=(a("4ml/"),a("zL8q")),w=a.n(N),k=(a("sVYa"),a("mtWM")),D=a.n(k).a.create({baseURL:"",withCredentials:!0,headers:{"Content-Type":"application/x-www-form-urlencoded;charset=utf-8"},transformRequest:[function(e){var t="";for(var a in e)!0===e.hasOwnProperty(a)&&(t+=encodeURIComponent(a)+"="+encodeURIComponent(e[a])+"&");return t}]});function x(e,t,a,r){D({method:e,url:t,data:"POST"===e||"PUT"===e?a:null,params:"GET"===e||"DELETE"===e?a:null}).then(function(e){r(e)}).catch(function(e){r(e)})}var P={get:function(e,t,a){return x("GET",e,t,a)},post:function(e,t,a){return x("POST",e,t,a)},put:function(e,t,a){return x("PUT",e,t,a)},delete:function(e,t,a){return x("DELETE",e,t,a)}};a("bs3D");r.default.use(i.e),r.default.use(i.b),r.default.prototype.$api=P,r.default.use(w.a,{size:"small",zIndex:3e3}),r.default.use(i.f),r.default.config.productionTip=!1,new r.default({el:"#app",router:_,components:{App:s},template:"<App/>"})},UEbX:function(e,t){},bs3D:function(e,t){},uxxp:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.fe63d53959c24a5dbc71.js.map