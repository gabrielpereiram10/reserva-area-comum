import { NavigationContainer } from "@react-navigation/native"

import { Loading } from "../components/loading";
import { useAuth } from "../contexts/auth";
import { AuthenticatedRoute } from "./authenticated.d.routes";
import { UnauthenticatedRoutes } from "./unauthenticated.d.routes"

export const Routes = () => {
    const { authData, isLoading } = useAuth()

    if (isLoading) {
        return <Loading />;
    }

    return (
        <NavigationContainer>
            {!authData?.token ? <UnauthenticatedRoutes /> : <AuthenticatedRoute />}
        </NavigationContainer>
    )
}