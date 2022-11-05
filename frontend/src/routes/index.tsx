import { NavigationContainer } from "@react-navigation/native"
import { UnauthenticatedRoutes } from "./unauthenticated.d.routes"

export const Routes = () => {
    return (
        <NavigationContainer>
            <UnauthenticatedRoutes />
        </NavigationContainer>
    )
}