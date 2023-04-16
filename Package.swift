// swift-tools-version:5.3
import PackageDescription

let environmentVersion = getenv("ENV_TYPE")
let version = String(cString: environmentVersion)

let xcFrameworkUrl = "https://maven.pkg.github.com/dariuszszlag/KBMultiplatform/com/dariusz/kbcore-kmmbridge/\(version)/kbcore-kmmbridge-\(version).zip"
let checksumUrl = "https://maven.pkg.github.com/dariuszszlag/KBMultiplatform/com/dariusz/kbcore-kmmbridge/\(version)/kbcore-kmmbridge-\(version).zip.md5"

let checksumString = String(contentsOf: checksumUrl)

let packageName = "kbcore"

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v14)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: xcFrameworkUrl,
            checksum: checksumString
        )
        ,
    ]
)