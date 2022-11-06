import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { HomeScreen } from "../screens/Home";

const Stack = createNativeStackNavigator()

export function AuthenticatedRoute() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="home"
                component={HomeScreen}
                options={{
                    headerShown: false
                }}
            />
        </Stack.Navigator>
    );
}