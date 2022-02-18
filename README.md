# App behavior
The display of data in the app is offline first with reload on demand, in a real world app it is ideal that the cached data has an expiration time but for the sake of example the app will load data only at app start when there are no posts in the local database or when the user triggers load on demand with the refresh button on home screen. If there is no data the list will show an empty message, if there is no internet while loading data from the network a message about it will appear.

The list of posts in each tab has live updates from the database, this way when an object is updated or deleted it will update the list automatically, the list has a `ListAdapter` with `DiffUtil` to efficiently perform the updates and to show smooth animation when deleting posts.

   [APK DOWNLOAD](https://drive.google.com/file/d/1ff26Pvbb70cRYKBHkTgGrRT9e3uUk3dp/view?usp=sharing)
   
# App arch

> This was intentionally over engineered for demonstration purposes.

I tried to follow **Clean Architecture** principles as much as I could between presentation, domain and data packages. For simplicity the remote and local layers are inside the same package. The main architecture of the app is `MVVM`

Managed the dependency injection with `Hilt`. I used `Retrofit` to load data from the network and `Room` to store data locally, both queries and requests are used with `coroutines` to perform the operation in background. Also used `Arrow` lib to handle responses with either a failure or valid result, I created my own failure objects, the idea is that these failure objects can have meta-data to pass to the presentation layer depending on what the error is, this way is easier to provide feedback to the user.

> The network requests are performed in a single thread but they could also be executed in parallel with a zip operator.

Object mappers are inside each data class but a mapper class can be used to avoid cross references that could break clean architecture.

For simplicity the `isRead` value update is done whenever a single post is queried, but ideally this should only be performed if `isRead` is `false`.

There is a base fragment to perform operations that are to be used in most fragments of the app, in this case the loading state, there is also a baseViewModel that handle failures that can be used to log crashes by sending throwable or exception I if the failure object accepts them as param. The idea of both is to have common blocks of code to reuse.

Both read and favorite state are completely local data, to avoid overwrite of these field an upsert function was created, this will do a partial update of the post (not including favorite and read flags) if the posts already exists in the database and it will insert it if the post does not exist in the database.
