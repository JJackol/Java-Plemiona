import Vue from "vue";
import axios from "axios";

// Full config:  https://github.com/axios/axios#request-config
axios.defaults.baseURL =
  process.env.baseURL || process.env.apiUrl || "http://localhost:8000";

const AUTH_TOKEN = localStorage.getItem("authorization_token");
if (AUTH_TOKEN) {
  axios.defaults.headers.common.Authorization = `Token ${AUTH_TOKEN}`;
}
axios.defaults.headers.post["Content-Type"] = "application/json";

let config = {
  baseURL: process.env.baseURL || process.env.apiUrl || "http://localhost:8000"
  // timeout: 60 * 1000, // Timeout
  // withCredentials: true, // Check cross-site Access-Control
};

const _axios = axios.create(config);
//
// _axios.interceptors.request.use(
//   function(config) {
//     console.log("intercept req gggogogo");
//     // Do something before request is sent
//     return config;
//   },
//   function(error) {
//     // Do something with request error
//     console.log("intercept req errr");
//     return Promise.reject(error);
//   }
// );
//
// // Add a response interceptor
// _axios.interceptors.response.use(
//   function(response) {
//     // Do something with response data
//     return response;
//   },
//   function(error) {
//     // Do something with response error
//     console.log("intercept response errr");
//     return Promise.reject(error);
//   }
// );

Plugin.install = function(Vue) {
  Vue.axios = _axios;
  window.axios = _axios;
  Object.defineProperties(Vue.prototype, {
    axios: {
      get() {
        return _axios;
      }
    },
    $axios: {
      get() {
        return _axios;
      }
    }
  });
};

Vue.use(Plugin);

export default _axios;
