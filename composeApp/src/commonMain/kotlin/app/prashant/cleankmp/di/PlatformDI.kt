package app.prashant.cleankmp.di

import org.koin.core.module.Module

/**
 * Declares an expect property platformModule to be implemented in platform-specific code.
 * This module provides platform-specific dependencies required for the application.
 *
 * @see Module
 *
 * @property platformModule The platform-specific Koin module.
 *
 * @constructor Creates an instance of [platformModule].
 *
 * @author Prashant Singh
 */
expect val platformModule: Module
