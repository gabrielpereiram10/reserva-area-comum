import { ResponseData } from "./http"

export type AuthContextData = {
    authData?: AuthData
    isAuthenticated: boolean
    isLoading: boolean
    signIn(signData: SignData): Promise<ResponseData<AuthData>>
    signOut(): void
}

export type AuthData = {
    user: {
        email: string
        name: string
    }
    token: {
        type: string
        accessToken: string
    }
}

export type SignData = {
    cpf: string
    password: string
}