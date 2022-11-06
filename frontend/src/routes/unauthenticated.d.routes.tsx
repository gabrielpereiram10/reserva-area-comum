import { createNativeStackNavigator } from "@react-navigation/native-stack";

import { RecoverPasswordScreen } from "../screens/RecoverPassword";
import { SiginInScreen } from "../screens/SignIn";

const Stack = createNativeStackNavigator()

export const UnauthenticatedRoutes = () => {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="login"
                component={SiginInScreen}
                options={{
                    headerShown: false
                }}
            />
            <Stack.Screen
                name="recover-password"
                component={RecoverPasswordScreen}
                options={{
                    title: "Recuperar Senha",
                    headerTitleAlign: "center"
                }}
            />
        </Stack.Navigator>
    )
}