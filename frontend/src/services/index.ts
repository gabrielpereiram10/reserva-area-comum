import axios from "axios";
import { REACT_APP_API_URI } from "@env"

const axiosInstance = axios.create({
    baseURL: `${REACT_APP_API_URI}/api`,
    headers: {
        post: {
            "Content-Type": "application/json"
        }
    }
})

axiosInstance.interceptors.response.use(config => {
    return config
}, error => {
    const { data } = error.response
    return Promise.reject({
        ...data,
        message: data?.message || "Erro de conexão"
    })
})

enum HEADER {
    AUTHORIZATION = "Authorization"
}

const setHeader = (header: HEADER, value: any) => {
    axiosInstance.defaults.headers[header] = value
}


export default {
    post: axiosInstance.post,
    get: axiosInstance.get,
    setHeader,
    HEADER
}