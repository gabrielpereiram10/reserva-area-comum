package com.gabriel.reservaareacomum.application.contracts;

import com.gabriel.reservaareacomum.application.auth.AuthHashCheckerInput;
import com.gabriel.reservaareacomum.application.auth.AuthHashGeneratorInput;
import com.gabriel.reservaareacomum.application.auth.AuthHashGeneratorOutput;

public interface IAuthUseCaseEncoder extends HashChecker<AuthHashCheckerInput>, HashGenerator<AuthHashGeneratorInput, AuthHashGeneratorOutput> {

}
