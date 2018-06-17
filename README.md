# Auth Interceptor
Interceptor that modifies requests so that they contain a token in their headers.

# Usage
1. Create token repository by implementing the following interface.
````
interface TokenStore {
    fun getToken(): String

    fun getTokenHeader(): String
}
````

2. Create AuthInterceptor providing it with a token repository.
3. Add interceptor to okHttp client.



````
  val repository = TokenRepository(PreferenceManager.getDefaultSharedPreferences(context))
  val authInterceptor = AuthInterceptor.create(repository)

  val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()
````
4. Proceed with Retrofit creation as usual.

Calls with header 'AUTH' will have a token added.

Calls with header 'NO_AUTH' will not have a token added.

Calls without any header will or will not have a token added, depending on what you specify in ````fun withDefault(defaultWithToken: Boolean): AuthInterceptor```` method.
````
'RestApiService.kt'
    
    @GET("users")
    fun getUsersDefault(): Call<JsonArray>

    @GET("users")
    @Headers(Header.AUTH)
    fun getUsersAuth(): Call<JsonArray>

    @GET("users")
    @Headers(Header.NO_AUTH)
    fun getUsersNoAuth(): Call<JsonArray>
````
