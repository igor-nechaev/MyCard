package com.nechaev.mycard.dagger

import com.nechaev.mycard.model.card.network.RetrofitCardHolders
import dagger.Component
import dagger.Module
import dagger.Provides


@Component(modules = [AppModule::class])
interface AppComponent {

}


@Module
object AppModule{

    @Provides
    fun provideRetrofitCardHolders() {

    }
}