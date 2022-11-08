import { NavigationContainer } from "@react-navigation/native"

import { Loading } from "../components/loading";
import { useAuth } from "../contexts/auth";
import { AuthenticatedRoute } from "./authenticated.d.routes";
import { UnauthenticatedRoutes } from "./unauthenticated.d.routes"

export const Routes = () => {
    const { isAuthenticated, isLoading } = useAuth()

    if (isLoading) {
        return <Loading />;
    }

    return (
        <NavigationContainer>
            {isAuthenticated ? <AuthenticatedRoute /> : <UnauthenticatedRoutes />}
        </NavigationContainer>
    )
}