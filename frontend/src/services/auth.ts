import { AuthData, SignData } from "../@types/auth"
import { ResponseData } from "../@types/http"
import api from "."

const authenticate = async (data: SignData): Promise<ResponseData<AuthData>> => {
    try {
        const response = await api.post("/auth", { ...data })
        const { user, token } = response.data
        return {
            success: true,
            data: {
                user: {
                    email: user?.userEmail,
                    name: user?.userName
                },
                token: {
                    type: token?.type,
                    accessToken: token?.token
                },
            }
        }
    } catch (error: any) {
        return {
            success: false,
            message: error.message
        }
    }
}

export const authService = {
    authenticate
}