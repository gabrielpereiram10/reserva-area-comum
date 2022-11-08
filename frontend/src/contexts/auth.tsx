import React, { useContext, useEffect, useState } from "react"
import { createContext } from "react"
import AsyncStorage from '@react-native-async-storage/async-storage'

import { AuthContextData, AuthData, SignData } from "../@types/auth"
import { authService } from "../services/auth"
import api from "../services"

export const AuthContext = createContext<AuthContextData>({} as AuthContextData)

export const AuthProvider = ({ children }: React.PropsWithChildren<{}>) => {
    const [authData, setAuthData] = useState<AuthData>()
    const [isLoading, setIsLoading] = useState<boolean>(true)

    useEffect(() => {
        loadStorageData()
    }, [])

    useEffect(() => {
        if (!authData) return api.setHeader(api.HEADER.AUTHORIZATION, null)
        api.setHeader(api.HEADER.AUTHORIZATION, `${authData.token.type} ${authData.token.accessToken}`)
    }, [authData])

    const loadStorageData = async (): Promise<void> => {
        try {
            const storedUser = await AsyncStorage.getItem("@User")
            const storeToken = await AsyncStorage.getItem("@Token")
            if (!storeToken || !storedUser) return

            const user = JSON.parse(String(storedUser))
            const token = JSON.parse(String(storeToken))
            setAuthData({ user, token })
        } catch (error) {
        } finally {
            setIsLoading(false)
        }
    }

    const signIn = async (signInData: SignData) => {
        const response = await authService.authenticate({ ...signInData })
        const { data, success, message } = response
        if (!success) return { message, success }

        setAuthData(data)
        await AsyncStorage.setItem('@User', JSON.stringify(data?.user))
        await AsyncStorage.setItem("@Token", JSON.stringify(data?.token))
        return { success }
    }

    const signOut = async () => {
        await AsyncStorage.removeItem("@User")
        await AsyncStorage.removeItem('@Token')
        setAuthData(undefined)
    }   

    return (
        <AuthContext.Provider
            value={{ isAuthenticated: !!authData?.token.accessToken, authData, isLoading, signIn, signOut }}
        >
            {children}
        </AuthContext.Provider>
    )
}

export const useAuth = (): AuthContextData => {
    const context = useContext(AuthContext);

    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }

    return context;
}