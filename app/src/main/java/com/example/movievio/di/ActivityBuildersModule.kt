package com.example.movievio.di

import com.example.movievio.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    //define subComponents here
    @ContributesAndroidInjector(
        modules = [FragmentBuildersModule::class,
            ViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}
