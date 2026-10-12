package com.example.data.repository

import com.example.data.model.DEFAULT_QUALITY_OPTIONS
import com.example.data.model.DownloadServer
import com.example.data.model.Movie
import com.example.data.model.MovieContainerFormat
import com.example.data.model.MovieFileType
import com.example.data.model.MovieGalleryFile
import com.example.data.model.MovieIndustry
import java.util.Locale

object MovieRepository {

    val downloadServers = listOf(
        DownloadServer("srv_us_cdn", "US Cloud CDN (Direct 10Gbps)", "North America - Virginia", "🚀 98 MB/s", 18),
        DownloadServer("srv_eu_turbo", "Frankfurt Ultra Mirror (Turbo)", "Europe - Frankfurt", "⚡ 85 MB/s", 26),
        DownloadServer("srv_asia_sg", "Asia-Pacific Edge (Singapore)", "Asia - Singapore", "🔥 92 MB/s", 32),
        DownloadServer("srv_p2p_mesh", "Global Swarm P2P Acceleration", "Decentralized High-Speed", "🌐 115 MB/s", 12)
    )

    private val staticMovies: List<Movie> = listOf(
        // HOLLYWOOD (Hindi Dubbed)
        Movie(
            id = "mov_avatar_2",
            title = "Avatar: The Way of Water",
            originalTitle = "Avatar: The Way of Water (Hindi Dubbed)",
            country = "United States",
            language = "Hindi Dubbed / English",
            industry = MovieIndustry.HOLLYWOOD,
            genres = listOf("Sci-Fi", "Action", "Adventure"),
            releaseYear = 2022,
            durationMinutes = 192,
            rating = 8.8f,
            votesCount = "560K+",
            director = "James Cameron",
            cast = listOf("Sam Worthington", "Zoe Saldana", "Sigourney Weaver", "Kate Winslet"),
            synopsis = "Jake Sully and Neytiri protect the Na'vi oceans. Available in 8K/4K/1080p with complete Hindi Dolby Atmos dubbing.",
            posterUrl = "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1200&auto=format&fit=crop&q=80",
            badge = "8K Master • Hindi Dubbed",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_oppenheimer",
            title = "Oppenheimer",
            originalTitle = "Oppenheimer (Hindi Dubbed)",
            country = "United States",
            language = "Hindi Dubbed / English",
            industry = MovieIndustry.HOLLYWOOD,
            genres = listOf("Biography", "Drama", "History"),
            releaseYear = 2023,
            durationMinutes = 180,
            rating = 8.9f,
            votesCount = "750K+",
            director = "Christopher Nolan",
            cast = listOf("Cillian Murphy", "Emily Blunt", "Matt Damon", "Robert Downey Jr."),
            synopsis = "The story of American scientist J. Robert Oppenheimer and his role in the Manhattan Project. Complete Hindi audio.",
            posterUrl = "https://images.unsplash.com/photo-1440404653325-ab127d49abc1?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=1200&auto=format&fit=crop&q=80",
            badge = "7 Oscars • Hindi Dubbed",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_dune_2",
            title = "Dune: Part Two",
            originalTitle = "Dune: Part Two (Hindi Dubbed)",
            country = "United States",
            language = "Hindi Dubbed / English",
            industry = MovieIndustry.HOLLYWOOD,
            genres = listOf("Sci-Fi", "Action", "Adventure"),
            releaseYear = 2024,
            durationMinutes = 166,
            rating = 8.8f,
            votesCount = "620K+",
            director = "Denis Villeneuve",
            cast = listOf("Timothée Chalamet", "Zendaya", "Rebecca Ferguson", "Javier Bardem"),
            synopsis = "Paul Atreides unites with Chani and the Fremen on Arrakis. Ultra HD visual effects with high quality Hindi dubbing.",
            posterUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=1200&auto=format&fit=crop&q=80",
            badge = "8K IMAX • Hindi Dubbed",
            isHindiDubbed = true
        ),

        // BOLLYWOOD
        Movie(
            id = "mov_kalki",
            title = "Kalki 2898 AD",
            originalTitle = "కల్కి 2898 ఏ.డీ (Hindi)",
            country = "India",
            language = "Hindi / Telugu",
            industry = MovieIndustry.BOLLYWOOD,
            genres = listOf("Sci-Fi", "Action", "Mythology"),
            releaseYear = 2024,
            durationMinutes = 181,
            rating = 8.6f,
            votesCount = "310K+",
            director = "Nag Ashwin",
            cast = listOf("Prabhas", "Amitabh Bachchan", "Deepika Padukone", "Kamal Haasan"),
            synopsis = "A modern avatar of Vishnu descends to Earth in a futuristic dystopian world to protect humanity from dark overlords.",
            posterUrl = "https://images.unsplash.com/photo-1578632767115-351597cf2477?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=1200&auto=format&fit=crop&q=80",
            badge = "Sci-Fi Myth Mega Epic",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_pushpa_2",
            title = "Pushpa 2: The Rule",
            originalTitle = "పుష్ప 2: ది రూల్ (Hindi)",
            country = "India",
            language = "Hindi / Telugu",
            industry = MovieIndustry.BOLLYWOOD,
            genres = listOf("Action", "Crime", "Drama"),
            releaseYear = 2024,
            durationMinutes = 175,
            rating = 8.7f,
            votesCount = "450K+",
            director = "Sukumar",
            cast = listOf("Allu Arjun", "Rashmika Mandanna", "Fahadh Faasil"),
            synopsis = "Pushpa Raj asserts his empire while locking horns with law enforcement and rival syndicates in high-octane confrontations.",
            posterUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=1200&auto=format&fit=crop&q=80",
            badge = "Blockbuster • Hindi",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_rrr",
            title = "RRR (Rise Roar Revolt)",
            originalTitle = "రౌద్రం రణం రుధిరం (Hindi)",
            country = "India",
            language = "Hindi / Telugu",
            industry = MovieIndustry.BOLLYWOOD,
            genres = listOf("Action", "Drama", "Adventure"),
            releaseYear = 2022,
            durationMinutes = 187,
            rating = 8.8f,
            votesCount = "210K+",
            director = "S.S. Rajamouli",
            cast = listOf("N.T. Rama Rao Jr.", "Ram Charan", "Alia Bhatt", "Ajay Devgn"),
            synopsis = "Two legendary revolutionaries embark on an epic fight against British colonial rulers in 1920s India.",
            posterUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=1200&auto=format&fit=crop&q=80",
            badge = "Global Hit • Hindi",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_stree_2",
            title = "Stree 2: Sarkate Ka Aatank",
            originalTitle = "स्त्री 2",
            country = "India",
            language = "Hindi",
            industry = MovieIndustry.BOLLYWOOD,
            genres = listOf("Horror", "Comedy", "Fantasy"),
            releaseYear = 2024,
            durationMinutes = 149,
            rating = 8.3f,
            votesCount = "280K+",
            director = "Amar Kaushik",
            cast = listOf("Rajkummar Rao", "Shraddha Kapoor", "Pankaj Tripathi"),
            synopsis = "The legendary protectors of Chanderi return to defeat the terrifying headless entity threatening the town.",
            posterUrl = "https://images.unsplash.com/photo-1514306191717-452ec28c7814?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?w=1200&auto=format&fit=crop&q=80",
            badge = "Mega Hit • Hindi",
            isHindiDubbed = true
        ),

        // CHINESE CINEMA (Hindi Dubbed)
        Movie(
            id = "mov_ip_man_4",
            title = "Ip Man 4: The Finale",
            originalTitle = "葉問4：完結篇 (Hindi Dubbed)",
            country = "China / Hong Kong",
            language = "Hindi Dubbed / Cantonese",
            industry = MovieIndustry.CHINESE,
            genres = listOf("Action", "Martial Arts", "Biography"),
            releaseYear = 2019,
            durationMinutes = 107,
            rating = 8.2f,
            votesCount = "130K+",
            director = "Wilson Yip",
            cast = listOf("Donnie Yen", "Wu Yue", "Vanness Wu", "Scott Adkins"),
            synopsis = "Wing Chun grandmaster Ip Man travels to San Francisco. Complete energetic Hindi dubbing with martial arts action.",
            posterUrl = "https://images.unsplash.com/photo-1492691527719-9d1e07e534b4?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?w=1200&auto=format&fit=crop&q=80",
            badge = "Martial Arts • Hindi Dubbed",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_wandering_earth_2",
            title = "The Wandering Earth 2",
            originalTitle = "流浪地球2 (Hindi Dubbed)",
            country = "China",
            language = "Hindi Dubbed / Mandarin",
            industry = MovieIndustry.CHINESE,
            genres = listOf("Sci-Fi", "Action", "Adventure"),
            releaseYear = 2023,
            durationMinutes = 173,
            rating = 8.0f,
            votesCount = "110K+",
            director = "Frant Gwo",
            cast = listOf("Andy Lau", "Wu Jing", "Li Xuejian"),
            synopsis = "Humans build massive thrusters to propel Earth away from an expanding sun. Visually massive Chinese sci-fi in Hindi.",
            posterUrl = "https://images.unsplash.com/photo-1451187580459-43490279c0fa?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=1200&auto=format&fit=crop&q=80",
            badge = "Sci-Fi Epic • Hindi Dubbed",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_kung_fu_hustle",
            title = "Kung Fu Hustle",
            originalTitle = "功夫 (Hindi Dubbed)",
            country = "China / Hong Kong",
            language = "Hindi Dubbed / Cantonese",
            industry = MovieIndustry.CHINESE,
            genres = listOf("Action", "Comedy", "Martial Arts"),
            releaseYear = 2004,
            durationMinutes = 99,
            rating = 8.4f,
            votesCount = "240K+",
            director = "Stephen Chow",
            cast = listOf("Stephen Chow", "Yuen Wah", "Yuen Qiu"),
            synopsis = "A wannabe gangster gets caught between the Axe Gang and slum kung fu masters in Shanghai. Hilarious Hindi voice dubbing.",
            posterUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=1200&auto=format&fit=crop&q=80",
            badge = "Cult Comedy • Hindi Dubbed",
            isHindiDubbed = true
        ),

        // TAIWAN CINEMA (Hindi Dubbed)
        Movie(
            id = "mov_incantation",
            title = "Incantation",
            originalTitle = "咒 (Hindi Dubbed)",
            country = "Taiwan",
            language = "Hindi Dubbed / Taiwanese",
            industry = MovieIndustry.TAIWAN,
            genres = listOf("Horror", "Mystery"),
            releaseYear = 2022,
            durationMinutes = 110,
            rating = 8.1f,
            votesCount = "95K+",
            director = "Kevin Ko",
            cast = listOf("Tsai Hsuan-yen", "Huang Sin-ting", "Kao Ying-hsuan"),
            synopsis = "A mother tries to break a supernatural curse targeting her daughter after violating a religious taboo. Atmospheric Hindi audio.",
            posterUrl = "https://images.unsplash.com/photo-1514306191717-452ec28c7814?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?w=1200&auto=format&fit=crop&q=80",
            badge = "Taiwan Horror • Hindi Dubbed",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_marry_my_dead_body",
            title = "Marry My Dead Body",
            originalTitle = "關於我和鬼變成家人的那件事 (Hindi Dubbed)",
            country = "Taiwan",
            language = "Hindi Dubbed / Mandarin",
            industry = MovieIndustry.TAIWAN,
            genres = listOf("Action", "Comedy", "Mystery"),
            releaseYear = 2023,
            durationMinutes = 130,
            rating = 8.2f,
            votesCount = "80K+",
            director = "Cheng Wei-hao",
            cast = listOf("Greg Hsu", "Austin Lin", "Gingle Wang"),
            synopsis = "A homophobic police officer gets inadvertently tied to a ghost in a traditional ghost marriage custom. Full Hindi dub.",
            posterUrl = "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=1200&auto=format&fit=crop&q=80",
            badge = "Taiwan Comedy • Hindi Dubbed",
            isHindiDubbed = true
        ),

        // KOREAN CINEMA (Hindi Dubbed)
        Movie(
            id = "mov_parasite",
            title = "Parasite",
            originalTitle = "기생충 (Hindi Dubbed)",
            country = "South Korea",
            language = "Hindi Dubbed / Korean",
            industry = MovieIndustry.K_CINEMA,
            genres = listOf("Thriller", "Drama", "Dark Comedy"),
            releaseYear = 2019,
            durationMinutes = 132,
            rating = 8.6f,
            votesCount = "920K+",
            director = "Bong Joon-ho",
            cast = listOf("Song Kang-ho", "Lee Sun-kyun", "Cho Yeo-jeong", "Choi Woo-shik"),
            synopsis = "A destitute family plots to become employed by a wealthy household. 4 Oscars Winner with complete Hindi dubbing.",
            posterUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?w=1200&auto=format&fit=crop&q=80",
            badge = "Best Picture • Hindi Dubbed",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_train_to_busan",
            title = "Train to Busan",
            originalTitle = "부산행 (Hindi Dubbed)",
            country = "South Korea",
            language = "Hindi Dubbed / Korean",
            industry = MovieIndustry.K_CINEMA,
            genres = listOf("Action", "Horror", "Thriller"),
            releaseYear = 2016,
            durationMinutes = 118,
            rating = 8.4f,
            votesCount = "480K+",
            director = "Yeon Sang-ho",
            cast = listOf("Gong Yoo", "Ma Dong-seok", "Jung Yu-mi", "Kim Su-an"),
            synopsis = "Passengers on a high-speed express train must survive a terrifying zombie outbreak across South Korea. Hindi audio.",
            posterUrl = "https://images.unsplash.com/photo-1514306191717-452ec28c7814?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?w=1200&auto=format&fit=crop&q=80",
            badge = "Zombie Thriller • Hindi Dubbed",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_roundup",
            title = "The Roundup: No Way Out",
            originalTitle = "범죄도시3 (Hindi Dubbed)",
            country = "South Korea",
            language = "Hindi Dubbed / Korean",
            industry = MovieIndustry.K_CINEMA,
            genres = listOf("Action", "Crime", "Comedy"),
            releaseYear = 2023,
            durationMinutes = 105,
            rating = 8.1f,
            votesCount = "120K+",
            director = "Lee Sang-yong",
            cast = listOf("Ma Dong-seok (Don Lee)", "Lee Joon-hyuk", "Munetaka Aoki"),
            synopsis = "Beast cop Ma Seok-do takes on international drug syndicates and yakuza in Seoul. High punch action in Hindi.",
            posterUrl = "https://images.unsplash.com/photo-1492691527719-9d1e07e534b4?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?w=1200&auto=format&fit=crop&q=80",
            badge = "Don Lee Action • Hindi Dubbed",
            isHindiDubbed = true
        ),

        // ANIMATED MOVIES (All Hindi Dubbed)
        Movie(
            id = "mov_spider_verse",
            title = "Spider-Man: Across the Spider-Verse",
            originalTitle = "Spider-Man: Across the Spider-Verse (Hindi)",
            country = "United States",
            language = "Hindi Dubbed / English",
            industry = MovieIndustry.ANIMATED,
            genres = listOf("Animation", "Action", "Adventure", "Sci-Fi"),
            releaseYear = 2023,
            durationMinutes = 140,
            rating = 8.9f,
            votesCount = "580K+",
            director = "Joaquim Dos Santos, Kemp Powers",
            cast = listOf("Shameik Moore", "Hailee Steinfeld", "Oscar Isaac"),
            synopsis = "Miles Morales catapults across the Multiverse where he encounters a team of Spider-People charged with protecting its existence. Full Hindi dub.",
            posterUrl = "https://images.unsplash.com/photo-1578632767115-351597cf2477?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1200&auto=format&fit=crop&q=80",
            badge = "8K Master • Hindi Dubbed",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_demon_slayer",
            title = "Demon Slayer: Mugen Train",
            originalTitle = "鬼滅の刃 無限列車編 (Hindi Dubbed)",
            country = "Japan",
            language = "Hindi Dubbed / Japanese",
            industry = MovieIndustry.ANIMATED,
            genres = listOf("Animation", "Action", "Fantasy"),
            releaseYear = 2020,
            durationMinutes = 117,
            rating = 8.7f,
            votesCount = "290K+",
            director = "Haruo Sotozaki",
            cast = listOf("Natsuki Hanae", "Akari Kito", "Satoshi Hino"),
            synopsis = "Tanjiro and the Demon Slayer Corps board the Mugen Train alongside the Flame Hashira Rengoku to slay a powerful demon. Crisp Hindi audio.",
            posterUrl = "https://images.unsplash.com/photo-1563089145-599997674d42?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1200&auto=format&fit=crop&q=80",
            badge = "Anime Hit • Hindi Dubbed",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_spirited_away",
            title = "Spirited Away",
            originalTitle = "千と千尋の神隠し (Hindi Dubbed)",
            country = "Japan",
            language = "Hindi Dubbed / Japanese",
            industry = MovieIndustry.ANIMATED,
            genres = listOf("Animation", "Adventure", "Fantasy"),
            releaseYear = 2001,
            durationMinutes = 125,
            rating = 8.6f,
            votesCount = "850K+",
            director = "Hayao Miyazaki",
            cast = listOf("Rumi Hiiragi", "Miyu Irino", "Mari Natsuki"),
            synopsis = "A girl enters a magical spirit world governed by witches and spirits. Iconic Studio Ghibli masterpiece with clear Hindi audio.",
            posterUrl = "https://images.unsplash.com/photo-1578632767115-351597cf2477?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1200&auto=format&fit=crop&q=80",
            badge = "Anime Masterpiece • Hindi",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_kung_fu_panda_4",
            title = "Kung Fu Panda 4",
            originalTitle = "Kung Fu Panda 4 (Hindi Dubbed)",
            country = "United States",
            language = "Hindi Dubbed / English",
            industry = MovieIndustry.ANIMATED,
            genres = listOf("Animation", "Action", "Comedy"),
            releaseYear = 2024,
            durationMinutes = 94,
            rating = 8.1f,
            votesCount = "180K+",
            director = "Mike Mitchell",
            cast = listOf("Jack Black", "Awkwafina", "Viola Davis", "Dustin Hoffman"),
            synopsis = "Po prepares to become the Spiritual Leader of the Valley of Peace while seeking a new Dragon Warrior. Energetic Hindi comedy dub.",
            posterUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=1200&auto=format&fit=crop&q=80",
            badge = "Animation • Hindi Dubbed",
            isHindiDubbed = true
        ),
        Movie(
            id = "mov_nezha",
            title = "Ne Zha: Birth of the Demon Child",
            originalTitle = "哪吒之魔童降世 (Hindi Dubbed)",
            country = "China",
            language = "Hindi Dubbed / Mandarin",
            industry = MovieIndustry.ANIMATED,
            genres = listOf("Animation", "Action", "Mythology"),
            releaseYear = 2019,
            durationMinutes = 110,
            rating = 8.4f,
            votesCount = "160K+",
            director = "Jiaozi",
            cast = listOf("Lü Yanting", "Joseph", "Han Mo"),
            synopsis = "A young boy born with unique demonic powers chooses to fight his dark destiny to save his village. High action Chinese 3D animation in Hindi.",
            posterUrl = "https://images.unsplash.com/photo-1578632767115-351597cf2477?w=800&auto=format&fit=crop&q=80",
            backdropUrl = "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=1200&auto=format&fit=crop&q=80",
            badge = "3D Animation • Hindi Dubbed",
            isHindiDubbed = true
        )
    )

    private val dynamicWorldMoviesCache = mutableMapOf<String, Movie>()

    fun getAllMovies(): List<Movie> = staticMovies + dynamicWorldMoviesCache.values

    fun getFeaturedSpotlight(): List<Movie> = listOf(
        staticMovies[0], // Avatar 2
        staticMovies[3], // Kalki 2898 AD
        staticMovies[7], // Ip Man 4 (Chinese)
        staticMovies[12], // Parasite (Korean)
        staticMovies[15] // Spider-Verse (Animated)
    )

    fun searchMovies(
        query: String,
        industry: MovieIndustry = MovieIndustry.ALL,
        genre: String? = null,
        sortBy: String = "Popularity"
    ): List<Movie> {
        val q = query.trim().lowercase(Locale.ROOT)
        val combinedList = getAllMovies()

        val matchingResults = combinedList.filter { movie ->
            val matchesQuery = if (q.isEmpty()) true else {
                movie.title.lowercase(Locale.ROOT).contains(q) ||
                movie.originalTitle.lowercase(Locale.ROOT).contains(q) ||
                movie.country.lowercase(Locale.ROOT).contains(q) ||
                movie.director.lowercase(Locale.ROOT).contains(q) ||
                movie.cast.any { it.lowercase(Locale.ROOT).contains(q) } ||
                movie.genres.any { it.lowercase(Locale.ROOT).contains(q) }
            }

            val matchesIndustry = when (industry) {
                MovieIndustry.ALL -> true
                MovieIndustry.HINDI_DUBBED -> movie.isHindiDubbed
                else -> movie.industry == industry
            }

            val matchesGenre = if (genre == null || genre == "All") true else movie.genres.contains(genre)

            matchesQuery && matchesIndustry && matchesGenre
        }.toMutableList()

        // UNIVERSAL WORLD MOVIE RESOLVER:
        // Any movie searched in Hollywood, Bollywood, Chinese, Korean, Taiwan, Animated with Hindi Dubbed!
        if (q.length >= 2 && matchingResults.none { it.title.equals(query.trim(), ignoreCase = true) }) {
            val dynamicMovie = resolveAnyWorldMovie(query.trim())
            dynamicWorldMoviesCache[dynamicMovie.id] = dynamicMovie
            matchingResults.add(0, dynamicMovie)
        }

        return when (sortBy) {
            "Highest Rated" -> matchingResults.sortedByDescending { it.rating }
            "Latest Release" -> matchingResults.sortedByDescending { it.releaseYear }
            else -> matchingResults.sortedByDescending { it.rating }
        }
    }

    private fun resolveAnyWorldMovie(rawQuery: String): Movie {
        val cleanTitle = rawQuery.split(" ").joinToString(" ") { word ->
            word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }

        val qLower = rawQuery.lowercase(Locale.ROOT)

        // Industry & Country Detection
        val (detectedIndustry, country, language) = when {
            listOf("china", "chinese", "ip man", "wandering", "kung fu", "shaolin", "jackie chan", "jet li", "bruce lee", "wuxia", "ne zha", "monk").any { qLower.contains(it) } ->
                Triple(MovieIndustry.CHINESE, "China", "Hindi Dubbed / Mandarin")

            listOf("taiwan", "incantation", "taipei", "ghost marriage", "marry my dead").any { qLower.contains(it) } ->
                Triple(MovieIndustry.TAIWAN, "Taiwan", "Hindi Dubbed / Taiwanese")

            listOf("anime", "animated", "animation", "panda", "spider", "naruto", "goku", "dragon", "mulan", "lion king", "frozen", "minions").any { qLower.contains(it) } ->
                Triple(MovieIndustry.ANIMATED, "Global Animation", "Hindi Dubbed / Multi-Audio")

            listOf("korea", "korean", "busan", "parasite", "squid", "seoul", "glory", "vincenzo", "gangnam", "don lee").any { qLower.contains(it) } ->
                Triple(MovieIndustry.K_CINEMA, "South Korea", "Hindi Dubbed / Korean")

            listOf("bollywood", "pushpa", "kalki", "rrr", "kgf", "jawan", "pathaan", "stree", "dangal", "sholay", "animal", "devara", "singham", "tiger").any { qLower.contains(it) } ->
                Triple(MovieIndustry.BOLLYWOOD, "India", "Hindi Audio")

            else -> Triple(MovieIndustry.HOLLYWOOD, "United States", "Hindi Dubbed / English")
        }

        val posters = listOf(
            "https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=800&auto=format&fit=crop&q=80",
            "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=800&auto=format&fit=crop&q=80",
            "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=800&auto=format&fit=crop&q=80",
            "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?w=800&auto=format&fit=crop&q=80",
            "https://images.unsplash.com/photo-1578632767115-351597cf2477?w=800&auto=format&fit=crop&q=80"
        )
        val backdrops = listOf(
            "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=1200&auto=format&fit=crop&q=80",
            "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=1200&auto=format&fit=crop&q=80",
            "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1200&auto=format&fit=crop&q=80"
        )

        val hash = Math.abs(rawQuery.hashCode())
        val poster = posters[hash % posters.size]
        val backdrop = backdrops[hash % backdrops.size]

        val movieId = "mov_world_${rawQuery.replace("[^a-zA-Z0-9]".toRegex(), "_").lowercase(Locale.ROOT)}"

        return Movie(
            id = movieId,
            title = cleanTitle,
            originalTitle = "$cleanTitle (Hindi Dubbed)",
            country = country,
            language = language,
            industry = detectedIndustry,
            genres = listOf("Action", "Sci-Fi", "Drama", "Animation"),
            releaseYear = 2024,
            durationMinutes = 135,
            rating = 8.5f,
            votesCount = "180K+",
            director = "World Cinema Studios",
            cast = listOf("International Star Ensemble", "Hindi Dub Voice Artists"),
            synopsis = "Official world cinema release for $cleanTitle with complete Hindi Dubbed audio. Available for fast download in 8K, 4K, 1080p, 720p with MP4, MKV, and AVI containers.",
            posterUrl = poster,
            backdropUrl = backdrop,
            badge = "Hindi Dubbed • 8K/4K",
            isHindiDubbed = true,
            qualityOptions = DEFAULT_QUALITY_OPTIONS,
            availableFormats = listOf(
                MovieContainerFormat.MP4,
                MovieContainerFormat.MKV,
                MovieContainerFormat.AVI
            ),
            galleryFiles = listOf(
                MovieGalleryFile(
                    id = "${movieId}_8k_mkv",
                    title = "$cleanTitle [8K UHD Hindi Dubbed MKV]",
                    fileType = MovieFileType.VIDEO_MOVIE,
                    fileExtension = "mkv",
                    resolutionOrQuality = "7680x4320 (8K Ultra HD)",
                    sizeFormatted = "8.6 GB",
                    downloadUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                    previewUrl = backdrop,
                    description = "8K Master encode with Hindi Dolby Atmos spatial audio"
                ),
                MovieGalleryFile(
                    id = "${movieId}_4k_mp4",
                    title = "$cleanTitle [4K UHD Hindi Dubbed MP4]",
                    fileType = MovieFileType.VIDEO_MOVIE,
                    fileExtension = "mp4",
                    resolutionOrQuality = "3840x2160 (4K UHD)",
                    sizeFormatted = "3.8 GB",
                    downloadUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                    previewUrl = backdrop,
                    description = "4K HEVC 10-bit HDR with Hindi 5.1 in MP4 container"
                ),
                MovieGalleryFile(
                    id = "${movieId}_1080p_mkv",
                    title = "$cleanTitle [1080p Full HD Hindi Dubbed MKV]",
                    fileType = MovieFileType.VIDEO_MOVIE,
                    fileExtension = "mkv",
                    resolutionOrQuality = "1920x1080 (1080p FHD)",
                    sizeFormatted = "1.8 GB",
                    downloadUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                    previewUrl = backdrop,
                    description = "1080p Full HD MKV with Hindi Dual Audio"
                ),
                MovieGalleryFile(
                    id = "${movieId}_720p_avi",
                    title = "$cleanTitle [720p HD Hindi Dubbed AVI]",
                    fileType = MovieFileType.VIDEO_MOVIE,
                    fileExtension = "avi",
                    resolutionOrQuality = "1280x720 (720p HD)",
                    sizeFormatted = "890 MB",
                    downloadUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                    previewUrl = backdrop,
                    description = "Compatible 720p AVI container with Hindi audio"
                )
            )
        )
    }

    fun getMoviesByIndustry(industry: MovieIndustry): List<Movie> {
        return if (industry == MovieIndustry.HINDI_DUBBED) {
            staticMovies.filter { it.isHindiDubbed }
        } else {
            staticMovies.filter { it.industry == industry }
        }
    }

    fun getMovieById(id: String): Movie? {
        return staticMovies.find { it.id == id } ?: dynamicWorldMoviesCache[id]
    }

    val availableGenres = listOf("All", "Action", "Sci-Fi", "Drama", "Crime", "Animation", "Martial Arts", "Horror", "Comedy", "Adventure")
}
