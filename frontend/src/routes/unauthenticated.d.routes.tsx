import { createNativeStackNavigator } from "@react-navigation/native-stack";
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
        </Stack.Navigator>
    )
}