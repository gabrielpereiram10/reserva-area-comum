package com.gabriel.reservaareacomum.application.contracts;

import com.gabriel.reservaareacomum.application.auth.HashCheckerIB;

public interface HashChecker {

    boolean check(HashCheckerIB input);
}
